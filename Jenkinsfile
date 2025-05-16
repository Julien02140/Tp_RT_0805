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
        stage('Debug WAR') {
            steps {
                sh 'ls -lh target/'
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

                    # On attend que ça monte
                    sleep 10
                '''
            }
        }
        stage('Setup Python Env and Run Selenium Tests') {
            steps {
                sh '''
                    # Créer l'environnement virtuel (test-env) s'il n'existe pas
                    python3 -m venv test-env

                    # Activer l'environnement virtuel
                    . test-env/bin/activate

                    # Mettre à jour pip et installer les dépendances nécessaires
                    pip install --upgrade pip
                    pip install -r requirements.txt

                    # Lancer les tests Selenium
                    python selenium-tests/test_selenium.py
                '''
            }
        }
        // stage('Locust Tests') {
        //     steps {
        //         sh '''
        //             . test-env/bin/activate
        //             locust -f locustfile.py --headless -u 10 -r 2 --run-time 1m --host http://10.11.17.50:8888 --html locust-report.html
        //         '''
        //         //archive le rapport HTML dans Jenkins
        //         archiveArtifacts artifacts: 'locust-report.html', fingerprint: true
        //     }
        // }
    }
}
