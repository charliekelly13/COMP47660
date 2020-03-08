<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Statistics</title>
    <link href="https://fonts.googleapis.com/css2" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div id="header">
        <img src="img/logo.png" width="200" alt="Logo"/>
        <div align="right">
            <a href="/settings">Settings</a>
            <a href="/logout">Log out</a>
        </div>
    </div>
<h1>University Statistics</h1>
<!-- Load d3.js -->
<script src="https://d3js.org/d3.v4.js"></script>

<h2>Gender</h2>
<div id="gender-stats"></div>

<h3>Nationality</h3>
<div id="nationality-stats"></div>

<script>
    d3.select('h2').attr('align',"center");
    d3.select('h3').style('color', 'darkblue');
    d3.select('h3').style('font-size', '24px');
    d3.select('h3').attr('align',"center");
    d3.select("#gender-stats").attr("align","center");

    // set the dimensions and margins of the graph
    var width = 450
    height = 450
    margin = 40

    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
    var radius = Math.min(width, height) / 2 - margin

    // append the svg object to the div called 'gender-stats'
    var svg = d3.select("#gender-stats")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    <!-- Create a div where the graph will take place -->


    // set the color scale
    var color = d3.scaleOrdinal()
        .domain(${gender})
        .range(["#1E90FF", "#FF69B4"])

    // Compute the position of each group on the pie:
    var pie = d3.pie()
        .value(function(d) {return d.value; })
    var data_ready = pie(d3.entries(${gender}))

    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
    svg
        .selectAll('whatever')
        .data(data_ready)
        .enter()
        .append('path')
        .attr('d', d3.arc()
            .innerRadius(0)
            .outerRadius(radius)
        )
        .attr('fill', function(d){ return(color(d.data.key)) })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)

    // shape helper to build arcs:
    var arcGenerator = d3.arc()
        .innerRadius(0)
        .outerRadius(radius)

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg
        .selectAll('mySlices')
        .data(data_ready)
        .enter()
        .append('text')
        .text(function(d){  return d.data.key+ ": "+d.data.value})
        .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
        .style("text-anchor", "middle")
        .style("font-size", 17)
</script>

<script>
    d3.select('h2').attr('align',"center");
    d3.select('h3').style('color', 'darkblue');
    d3.select('h3').style('font-size', '24px');
    d3.select('h3').attr('align',"center");
    d3.select("#nationality-stats").attr("align","center");

    // set the dimensions and margins of the graph
    var width = 450
    height = 450
    margin = 40

    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
    var radius = Math.min(width, height) / 2 - margin

    // append the svg object to the div called 'nationality-stats'
    var svg = d3.select("#nationality-stats")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    <!-- Create a div where the graph will take place -->



    // set the color scale
    var color = d3.scaleOrdinal(d3.schemeCategory10);

    // Compute the position of each group on the pie:
    var pie = d3.pie()
        .value(function(d) {return d.value; })
    var data_ready = pie(d3.entries(${nationality}))

    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
    svg
        .selectAll('whatever')
        .data(data_ready)
        .enter()
        .append('path')
        .attr('d', d3.arc()
            .innerRadius(0)
            .outerRadius(radius)
        )
        .attr("fill",function(d,i){return color(i);})
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)

    // shape helper to build arcs:
    var arcGenerator = d3.arc()
        .innerRadius(0)
        .outerRadius(radius)

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg
        .selectAll('mySlices')
        .data(data_ready)
        .enter()
        .append('text')
        .text(function(d){  return d.data.key+ ": "+d.data.value})
        .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
        .style("text-anchor", "middle")
        .style("font-size", 17)

</script>
<a href="./logout">Log out</a>
</body>
</html>