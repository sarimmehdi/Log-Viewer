pipeline {
    agent any

    options {
        buildDiscarder(logRotator(daysToKeepStr: '14', numToKeepStr: '30'))
    }

    environment {
        GRADLE_OPTS = "-Dorg.gradle.daemon=false -Dorg.gradle.parallel=false"
    }

    stages {
        stage('Prepare') {
            steps {
                checkout scm
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Analysis & Tests') {
            parallel {
                stage('Static Analysis') {
                    steps {
                        sh './gradlew ktlintCheck detekt'
                    }
                }
                stage('Architecture Tests') {
                    steps {
                        sh './gradlew testDebugUnitTest --tests "*ArchTest*"'
                    }
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
            mail to: 'sarim.mehdi.550@gmail.com',
                 subject: "❌ Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "See Jenkins for details: ${env.BUILD_URL}"
        }
    }
}
