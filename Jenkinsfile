pipeline {
  agent any
	
  environment {
    ANDROID_HOME = '/var/lib/jenkins/Android/Sdk' 
  }

  stages {
    stage('Clean') {
      steps {
        echo 'Cleaning...'
        sh "android-launcher/palingenesis clean"
      }
    }
    stage('Build') {
      steps {
        echo 'Building...'
        sh "android-launcher/palingenesis build"
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
      }
    }
  }
}
