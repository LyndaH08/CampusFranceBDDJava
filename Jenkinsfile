pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
        jdk 'Java 21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/LyndaH08/CampusFranceBDDJava.git'
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean compile"
            }
        }

        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
    }

    post {
        always {
            //  Publier le JSON pour le plugin Cucumber Reports
            cucumber 'target/cucumber-reports/Cucumber.json'

            // Archiver le HTML pour pouvoir le télécharger
            archiveArtifacts artifacts: 'target/cucumber-reports/Cucumber.html', allowEmptyArchive: true
        }
    }
}
