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

        stage('Analysis & Tests') {
            parallel {
                stage('Ktlint') {
                    steps {
                        sh './gradlew ktlintCheck'
                    }
                }
                stage('Static Analysis') {
                    steps {
                        sh './gradlew detekt'
                    }
                }
//                 stage('Android Lint') {
//                     steps {
//                         sh './gradlew lintDebug'
//                     }
//                 }
//                 stage('Architecture Tests') {
//                     steps {
//                         sh './gradlew testDebugUnitTest --tests "*ArchTest*"'
//                     }
//                 }
            }
        }

//         stage('Assemble Release') {
//             steps {
//                 sh './gradlew assembleRelease'
//             }
//         }
//
//         stage('Archive Artifacts & Reports') {
//             steps {
//                 archiveArtifacts artifacts: '**/build/reports/**/*.html, **/build/outputs/apk/release/*.apk', fingerprint: true
//             }
//         }
    }

    post {
        always {
            junit '**/build/test-results/**/*.xml'
        }
        failure {
            mail to: 'sarim.mehdi.550@gmail.com',
                 subject: "❌ Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "See Jenkins for details: ${env.BUILD_URL}"
        }
    }
}
