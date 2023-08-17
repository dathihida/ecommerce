let host = "http://localhost:8080/rest";
const app = angular.module("userapp",[]);
app.controller("userctrl", function($scope, $http){
    $scope.form={};
    $scope.items = [];
    $scope.reset = function(){
        
    }

    $http.get("http://localhost:8080/rest/authorities/rest/roleauthorities").then(resp => {
        $scope.db = resp.data;
    })

    //create account
    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/newaccounts`;
        $http.post(url, item).then(resp=>{
            $scope.items.push(item);
            $scope.reset();
            console.log(item.username);
            
            console.log("usernew", resp);
            
            $scope.createAuthority(item.username)
            window.location.href = `http://localhost:8080/security/login/success`;
            console.log("ten:", item.username)
        }).catch(error=>{
            console.log(error);
        })
    }

    $scope.createAuthority= function(username){
        var authority = {
            account:{username:username},
            role:{id:$scope.db.roles="CUST"}
        };

        console.log(authority.username)
        console.log(authority.role)

        $http.post('http://localhost:8080/rest/authorities',authority).then(resp=>{
            $scope.db.authorities.push(resp.data);
        })
        
    }
    	//upload hinh anh
	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('http://localhost:8080/rest/upload/images',data,{
			transformRequest:angular.indentity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form.photo = resp.data.name;
		}).catch(error =>{
			alert("loi updoad hinh")
			console.log(error)
		})
	}
})