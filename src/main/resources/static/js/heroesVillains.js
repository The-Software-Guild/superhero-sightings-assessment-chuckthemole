//$(document).ready(function(){
$("#heroesVillains_btn").click(function(e) {
    var banner = '<h1>All Heroes/Villains</h1>';
    var div = $('#banner_allHeroesVillains');
    div.html("");
    div.append(banner);
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/api/superhero/getHerosAndVillains',
        success: function(superArray) {
            var superDiv = $('#allHeroesVillains');
            superDiv.html("");

            $.each(superArray, function(index, heroVillain) {
                var superInfo = '<p>';
                superInfo += 'Name: ' + heroVillain.name + '<br>';
                superInfo += 'Is Hero: ' + heroVillain.isHero + '<br>';
                superInfo += '</p><hr>';

                superDiv.append(superInfo);
            })
        },
        error: function() {
            alert('FAILURE!');
        }
    })
});

$("#locations_btn").click(function(e) {
    var banner = '<h1>All Locations</h1>';
    var div = $('#banner_allHeroesVillains');
    div.html("");
    div.append(banner);
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/api/superhero/getLocations',
        success: function(superArray) {
            var locDiv = $('#allHeroesVillains');
            locDiv.html("");

            $.each(superArray, function(index, location) {
                var locInfo = '<p>';
                locInfo += 'Location ID: ' + location.id + '<br>';
                locInfo += 'Latitude: ' + location.latitude + '<br>';
                locInfo += 'Longitude: ' + location.longitude + '<br>';
                locInfo += '</p><hr>';

                locDiv.append(locInfo);
            })
        },
        error: function() {
            alert('FAILURE!');
        }
    })
});