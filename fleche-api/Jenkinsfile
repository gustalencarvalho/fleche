pipeline {
    agent any

    environment {
        IMAGE_NAME = 'fleche-api'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run --rm $IMAGE_NAME'
            }
        }
    }
}
