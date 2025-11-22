pipeline {
    agent any

    tools {
        jdk 'Java 17'
    }

    options {
        buildDiscarder(logRotator(daysToKeepStr: '14', numToKeepStr: '30'))
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        ANDROID_HOME = '/usr/lib/android-sdk'
        GRADLE_USER_HOME = "${env.WORKSPACE}/.gradle"
        PATH = "${JAVA_HOME}/bin:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${env.PATH}"
        GRADLE_OPTS = "-Dorg.gradle.daemon=false -Dorg.gradle.parallel=false"
    }

    stages {

        stage('Prepare') {
            steps {
                checkout scm
                sh 'chmod +x ./gradlew'
                sh '''
                    mkdir -p $GRADLE_USER_HOME
                    chown -R jenkins:jenkins $GRADLE_USER_HOME
                '''
            }
        }

        stage('Static Analysis') {
            parallel {
                stage('Ktlint') {
                    steps {
                        sh './gradlew ktlintCheck'
                    }
                }
                stage('Detekt') {
                    steps {
                        sh './gradlew detekt'
                    }
                }
                stage('Android Lint') {
                    steps {
                        sh './gradlew lintDebug'
                    }
                }
            }
        }

        stage('Tests') {
            steps {
                sh './gradlew app:testDebugUnitTest'
                sh './gradlew testDebugUnitTest'
                sh './gradlew robolectricTest'
            }
        }

        stage('Jacoco Report') {
            steps {
                sh './gradlew jacocoAggregatedReport'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv('sonarqube') {
                        sh './gradlew sonar -Dsonar.token=$SONAR_TOKEN'
                    }
                }
            }
        }

        stage('SonarQube Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Assemble Release') {
            steps {
                sh './gradlew assembleRelease'
            }
        }

        stage('Archive Artifacts & Reports') {
            steps {
                archiveArtifacts artifacts: '**/build/reports/**/*.html, **/build/outputs/apk/release/*.apk', fingerprint: true
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/**/*.xml'
        }
        failure {
            withCredentials([string(credentialsId: 'EMAIL_ADDRESS', variable: 'EMAIL')]) {
                mail to: "${EMAIL}",
                     subject: "❌ Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "See Jenkins for details: ${env.BUILD_URL}"
            }
        }
    }
}
