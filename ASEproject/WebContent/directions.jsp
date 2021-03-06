<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="" data-ng-app="GoogleDirection">
<head>
    <meta t="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Location Direction</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"
            type="application/javascript"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
    <link rel="stylesheet" href="css/main.css">

    <style>
        #map-canvas {
            width: 650px;
            height: 450px;
        }

        body {
            background: url(http://mymaplist.com/img/parallax/back.png);
            background-color: #444;
            background: url(http://mymaplist.com/img/parallax/pinlayer2.png),
            url(http://mymaplist.com/img/parallax/pinlayer1.png),
            url(http://mymaplist.com/img/parallax/back.png);
        }

        .vertical-offset-100 {
            padding-top: 100px;
        }
    </style>
</head>

<body>
<div align="center">
            <div  class=" col-md-12" >
                <div class="panel-heading">
                    <div class="panel-heading">
                    <h1 style="color:white">Plan B</h1>
                       <div><table> 
                        <td> <h2> <u><a href="home.jsp">Home</a></u></h2> </td> <td> <h2> <u><a href="Login.html">Logout</a></u></h2></td>
                   </table></div> </div>            
                </div>
            </div>
        </div>


<div align="center">
    <div class="wrapp" ng-controller="googlemapoutput">
        <div class=" col-md-6">
            <div id="map-canvas" style="height: 400px; float: left;"></div>
        </div>
        <div class=" col-md-6">
            <div id="panel" style="background-color: white"></div>
        </div>
    </div>
</div>
</body>
</html>


<script>
    var query_string = {};
    var query = window.location.search.substring(1);
    var address = query.split("=");
    //alert(address[1]);
    var ad = decodeURIComponent(address[1]);
    alert(ad);
    angular.module('GoogleDirection', [])
            .controller('googlemapoutput', function ($scope) {

                var map;
                var panel;
                var mapOptions;
                var directionsDisplay = new google.maps.DirectionsRenderer({
                    draggable: true
                });
                var directionsService = new google.maps.DirectionsService();

                $scope.initialize = function () {

                    console.log("Hey");

                    navigator.geolocation.getCurrentPosition(function (position) {
                        console.log(position)

                        var pos = new google.maps.LatLng(
                                position.coords.latitude,
                                position.coords.longitude);

                        $scope.currentLocation = pos;
                        var mapOptions = {
                            zoom: 10,
                            center: $scope.currentLocation
                        };

                        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

                        //Route Calculation

                        directionsDisplay.setPanel(document.getElementById('panel'));
                        var end = ad;
                        var request = {
                            origin: $scope.currentLocation,
                            destination: end,
                            travelMode: google.maps.TravelMode.DRIVING
                        };

                        directionsService.route(request, function (response, status) {
                            if (status == google.maps.DirectionsStatus.OK) {
                                directionsDisplay.setMap(map);
                                directionsDisplay.setDirections(response);
                                console.log(status);
                            }

                        });
                    });
                };

                //  directionsDisplay.setPanel(document.getElementById('panel'));
                $scope.calcRoute = function () {
                    // var end = document.getElementById('endlocation').value;
                    var end = ad;
                    var request = {
                        origin: $scope.currentLocation,
                        destination: end,
                        travelMode: google.maps.TravelMode.DRIVING
                    };

                    directionsService.route(request, function (response, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setMap(map);
                            directionsDisplay.setDirections(response);
                            console.log(status);
                        }

                    });
                };

                google.maps.event.addDomListener(window, 'load', $scope.initialize);

            });


</script>