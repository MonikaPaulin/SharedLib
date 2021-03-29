void call(){
checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[credentialsId: 'cafd918a-206d-4a9d-8165-5bc14ec467a8', url: 'https://github.com/MonikaPaulin/Release-Report-Jenkins-Pipeline.git']]]
}
