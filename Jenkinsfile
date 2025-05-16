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
        stage('Export Docker Image') {
            steps {
                sh 'docker save projetrt0910 > projetrt0910.tar'
            }
        }
        stage('Copy to Preprod') {
            steps {
                sh 'scp projetrt0910.tar urca@10.11.17.50:/tmp/'
            }
        }
        stage('Deploy on Preprod') {
            steps {
                sh '''
                    ssh urca@10.11.17.50 "docker rm -f projetrt0910-container || true"
                    ssh urca@10.11.17.50 "docker load < /tmp/projetrt0910.tar"
                    ssh urca@10.11.17.50 "docker run -d --name projetrt0910-container -p 8888:8080 projetrt0910"
                '''
            }
        }
        stage('Selenium Tests') {
            steps {
                // Exécuter le test Selenium (headless) en local (sur la machine Jenkins)
                sh '''
                    source locust-env/bin/activate
                    python selenium-tests/test_selenium.py
                '''
            }
        }
    }
}
