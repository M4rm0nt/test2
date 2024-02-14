angular.module('personApp', [])
    .controller('PersonController', function($scope, $http) {
        $scope.persons = [];
        $scope.newPerson = {};

        $scope.loadPersons = function() {
            $http.get('http://localhost:8080/person/all').then(function(response) {
                $scope.persons = response.data;
            });
        };

        $scope.addPerson = function() {
            $http.post('http://localhost:8080/person/add', $scope.newPerson).then(function(response) {
                $scope.persons.push(response.data);
                $scope.newPerson = {};
            });
        };

        // Initial load of persons
        $scope.loadPersons();
    });
