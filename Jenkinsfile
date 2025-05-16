pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Julien02140/Tp_RT_0805.git'
            }
        }
        stage('Build Maven') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t projetrt0910 .'
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker rm -f projetrt0910-container || true'
                sh 'docker run -d --name projetrt0910-container -p 8080:8080 projetrt0910'
            }
        }
    }
}
