pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Dorg.gradle.daemon=false -Dorg.gradle.parallel=false"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

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

        stage('Assemble Release') {
            steps {
                sh './gradlew assembleRelease'
            }
        }

        stage('Archive Reports and APK') {
            steps {
                // Archive code analysis and test reports
                archiveArtifacts artifacts: '**/build/reports/ktlint/**/*.html', fingerprint: true
                archiveArtifacts artifacts: '**/build/reports/detekt/**/*.html', fingerprint: true
                archiveArtifacts artifacts: '**/build/reports/tests/**/*.html', fingerprint: true
                // Archive release APK
                archiveArtifacts artifacts: '**/build/outputs/apk/release/*.apk', fingerprint: true
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
