angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    if ($localStorage.marketUser){
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime()/1000);
            if (currentTime > payload.exp){
                console.log("Token has expired");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';

            }
        } catch (e){
        }
        if ($localStorage.marketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
        }
    }

    $scope.setMinAndMax= function (minPrice, maxPrice){
        $scope.minPrice = minPrice;
        $scope.maxPrice = maxPrice;
        $scope.loadProducts();
    }

    $scope.tryToAuth = function (){
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response){
            });
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        // $scope.user = null;
    }

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    }

    $scope.isUserLoggedIn = function () {
      if($localStorage.marketUser) {
          return true;
      } else {
          return false;
      }
    }

    // $scope.authCheck = function () {
    //     $http.get('http://localhost:5555/core/auth_check').then(function (response){
    //         alert(response.data.value);
    //     });
    // }

    $scope.loadProducts = function () {
        $http({method:'GET', url:'http://localhost:5555/core/api/v1/products', params: {'minPrice':$scope.minPrice, 'maxPrice':$scope.maxPrice}})
            .then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:5555/core/api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:5555/core/api/v1/products/'+ productId).then(function (response){
            $scope.loadProducts();
        })
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }
    
    $scope.removeFromCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addOrder = function (){
        $http.get('http://localhost:5555/core/api/v1/order').then(function (response){
            alert(response.data.title);
            $scope.clearCart();
        })
    }
    
    $scope.loadProducts();
    $scope.loadCart();
});