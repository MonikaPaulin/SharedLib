void checkout(def url, def branch){
checkout(
[$class: 'GitSCM', 
branches: [[name: '*/master']], 
extensions: [], 
userRemoteConfigs: [
[credentialsId: 'cafd918a-206d-4a9d-8165-5bc14ec467a8', 
url: 'https://github.com/MonikaPaulin/SharedLib.git']
]])
}