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
                // Remplace "user" et "IP_PREPROD" par tes vrais identifiants
                sh 'scp projetrt0910.tar urca@:/tmp/'
            }
        }
        stage('Deploy on Preprod') {
            steps {
                // On lance les commandes SSH à distance sur le serveur de préprod
                sh '''
                    ssh user@IP_PREPROD "docker rm -f projetrt0910-container || true"
                    ssh user@IP_PREPROD "docker load < /tmp/projetrt0910.tar"
                    ssh user@IP_PREPROD "docker run -d --name projetrt0910-container -p 8888:8080 projetrt0910"
                '''
            }
        }
    }
}
