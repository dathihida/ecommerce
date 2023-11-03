const app = angular.module("app", []);
app.controller("product-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		//load product
		$http.get("http://localhost:8080/rest/products").then(resp =>{
			$scope.items = resp.data;
			$scope.items.forEach(item =>{
				item.createDate = new Date(item.createDate)
			})
		});
		//load categories
		$http.get("http://localhost:8080/rest/categories").then(resp =>{
			$scope.cates = resp.data;
		});	
	}
	$scope.initialize();
	
	//edit
	$scope.edit = function(p){
		$scope.form = angular.copy(p);
		 window.scrollTo(0, 0);
	}
	
	//upload hinh anh
	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('http://localhost:8080/rest/upload/images',data,{
			transformRequest:angular.indentity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form.image = resp.data.name;
		}).catch(error =>{
			alert("loi updoad hinh")
			console.log(error)
		})
	}
	//reset
	$scope.reset= function(){
		$scope.form = {
			createDate: new Date(),
			image:'cloud-upload.jpg',
			available: true
		}
	}
	//them moi
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp=>{
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("them moi thanh cong");
		}).catch(error=>{
			alert("loi them moi");
			console.log(error);
		})
	}
	//cap nhat
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp=>{
			var index = $scope.items.findIndex(p=> p.id == item.id);
			$scope.items[index] = item;
			alert("cap nhat thanh cong");
		}).catch(error=>{
			alert("loi cap nhat");
			console.log(error);
		})
	}
	//delete
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Xoa thanh cong");
		}).catch(error=>{
			alert("loi xoa");
			console.log(error);
		})
	}
	//phan trang
	
	$scope.pager = {
		page:0,
		size:10,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.items.length/ this.size)
		},
		first(){
			this.page=0;
		},
		prev(){
			this.page--;
		},
		next(){
			this.page++;
		},
		last(){
			this.page = this.count-1;
		}
	}
})