pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Obteniendo codigo desde GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando con Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Construyendo imagen Docker...'
                sh 'docker build -t hotel-api:latest .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Desplegando en contenedor...'
                sh '''
                    docker stop hotel-tomcat || true
                    docker rm hotel-tomcat || true
                    docker run -d \
                        --name hotel-tomcat \
                        -p 9090:8080 \
                        hotel-api:latest
                '''
            }
        }

        stage('Verificar') {
            steps {
                echo 'Verificando que la app responde...'
                sh 'sleep 15 && curl -f http://localhost:9090/api/health'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completado exitosamente!'
        }
        failure {
            echo 'Pipeline fallo. Revisa los logs.'
        }
    }
}