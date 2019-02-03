pipeline {
    agent any
	
    environment {
	ANDROID_HOME = '/var/lib/jenkins/Android/Sdk' 
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh "./gradlew build"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
		sh "./gradlew test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
