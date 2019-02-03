projects = [
  'android-launcher', 
  'android-ikaros', 
  'android-yumi', 
  'meteor-sisyfos'
]

pipeline {
  agent any
	
  environment {
    ANDROID_HOME = '/var/lib/jenkins/Android/Sdk' 
  }

  stages {
    stage('Init') {
      steps {
        echo 'Init...'
        init(projects)
      }
    }
    stage('Clean') {
      steps {
        echo 'Cleaning...'
        clean(projects)
      }
    }
    stage('Build') {
      steps {
        echo 'Building...'
        build(projects)
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
        test(projects)
      }
    }
    stage('Analysis') {
      steps {
        echo 'Analysis...'
        analysis(projects)
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
        deploy(projects)
      }
    }
  }
}

@NonCPS
def init(projects) {
    projects.each { project ->
        sh "${project}/palingenesis init"
    }
}

@NonCPS
def clean(projects) {
    projects.each { project ->
        sh "${project}/palingenesis clean"
    }
}

@NonCPS
def build(projects) {
    projects.each { project ->
        sh "${project}/palingenesis build"
    }
}

@NonCPS
def test(projects) {
    projects.each { project ->
        sh "${project}/palingenesis test"
    }
}

@NonCPS
def analysis(projects) {
    projects.each { project ->
        sh "${project}/palingenesis analysis"
    }
}

@NonCPS
def deploy(projects) {
    projects.each { project ->
        sh "${project}/palingenesis deploy"
    }
}

