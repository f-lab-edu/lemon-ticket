pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew build' // Gradle을 사용하여 프로젝트 빌드
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test' // Gradle을 사용하여 테스트 실행
            }
        }
        stage('Deploy') {
            steps {
                // 배포 단계 실행
            }
        }
    }
    post {
        success {
            emailext(
                subject: "Build Success - ${env.JOB_NAME} - #${env.BUILD_NUMBER}",
                body: "Build Successful.",
                to: "peten@kakao.com"
            )
        }
        failure {
            emailext(
                subject: "Build Failure - ${env.JOB_NAME} - #${env.BUILD_NUMBER}",
                body: "Build Failed.",
                to: "peten@kakao.com"
            )
            script {
                // 실패한 경우 해당 커밋을 되돌리는 단계
                git 'reset --hard HEAD^'
            }
        }
    }
}
