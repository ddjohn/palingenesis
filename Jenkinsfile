pipeline {
  agent any
	
  environment {
    ANDROID_HOME = '/var/lib/jenkins/Android/Sdk' 
  }

  stages {
    stage('Init') {
      steps {
        echo 'Init...'
        sh "android-launcher/palingenesis init"
        sh   "android-ikaros/palingenesis init"
        sh   "meteor-sisyfos/palingenesis init"
        sh   "meteor-waterplant/palingenesis init"
      }
    }
    stage('Clean') {
      steps {
        echo 'Cleaning...'
        sh "android-launcher/palingenesis clean"
        sh   "android-ikaros/palingenesis clean"
        sh   "meteor-sisyfos/palingenesis clean"
        sh   "meteor-waterplant/palingenesis clean"
      }
    }
    stage('Build') {
      steps {
        echo 'Building...'
        sh "android-launcher/palingenesis build"
        sh   "android-ikaros/palingenesis build"
        sh   "meteor-sisyfos/palingenesis build"
        sh   "meteor-waterplant/palingenesis build"
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
        sh "android-launcher/palingenesis test"
        sh   "android-ikaros/palingenesis test"
        sh   "meteor-sisyfos/palingenesis test"
        sh   "meteor-waterplant/palingenesis test"
      }
    }
    stage('Analysis') {
      steps {
        echo 'Analysis...'
        sh "android-launcher/palingenesis analysis"
        sh   "android-ikaros/palingenesis analysis"
        sh   "meteor-sisyfos/palingenesis analysis"
        sh   "meteor-waterplant/palingenesis analysis"
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
        sh "android-launcher/palingenesis deploy"
        sh   "android-ikaros/palingenesis deploy"
        sh   "meteor-sisyfos/palingenesis deploy"
        sh   "meteor-waterplant/palingenesis deploy"
      }
    }
  }
}
