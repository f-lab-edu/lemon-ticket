pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment {
        TELEGRAM_BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
        TELEGRAM_CHAT_ID = credentials('TELEGRAM_CHAT_ID')
    }
    stages {
        stage('Build') {
            steps {
                echo '실행중' // 실행 중에 메시지 출력
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
                echo '배포 단계 실행' // 배포 단계 실행 메시지 출력
                // 실제 배포 작업이 여기에 추가됩니다.
            }
        }
    }
    post {
        always {
            echo '실행완료' // 실행이 완료되면 메시지 출력
        }
        success {
            script {
                def currentDate = sh(script: 'date "+%Y-%m-%d %H:%M:%S"', returnStdout: true).trim()
                sh """
                curl -s -X POST https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage \
                    -d chat_id=${TELEGRAM_CHAT_ID} \
                    -d text="[${currentDate}] 성공(${env.BUILD_NUMBER})"
                """
            }
        }
        failure {
            script {
                def currentDate = sh(script: 'date "+%Y-%m-%d %H:%M:%S"', returnStdout: true).trim()
                sh """
                curl -s -X POST https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage \
                    -d chat_id=${TELEGRAM_CHAT_ID} \
                    -d text="[${currentDate}] 실패(${env.BUILD_NUMBER})"
                """
                // 실패한 경우 해당 커밋을 되돌리는 단계
                sh 'git reset --hard HEAD^'
            }
        }
    }
}
