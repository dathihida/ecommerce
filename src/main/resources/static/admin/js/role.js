var app = angular.module("app",[]);
app.controller("ctrl", function($scope, $http){
    $http.get("http://localhost:8080/rest/authorities/rest/roleauthorities").then(resp => {
        $scope.db = resp.data;
    })

    $scope.index_of=function(username, role){
        return $scope.db.authorities
        .findIndex( a=> a.account.username == username && a.role.id == role)
    }

    $scope.update = function(username, role){
        var index = $scope.index_of(username, role);
        if(index>=0){
            var id = $scope.db.authorities[index].id;
            $http.delete(`http://localhost:8080/rest/authorities/rest/roleauthorities/${id}`).then(resp=>{
                $scope.db.authorities.splice(index,1);
                alert("Da thu hoi quyen: "+ role +"cho"+username);
            })
        }else{
            var authority = {
                account:{username:username},
                role:{id:role}
            };
            $http.post('http://localhost:8080/rest/authorities/rest/roleauthorities',authority).then(resp=>{
                $scope.db.authorities.push(resp.data);
                alert("Da cap quyen: "+ role +"cho"+username);
            })
        }
    }
})