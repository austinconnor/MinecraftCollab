// function createPlot(folder_path, event_name, div_name, random_color) {
//     var data = [];

//     function createTrace(data, filename, player, mode, type, random_color){

//         d3.csv(filename, function(err, rows) {
        
//             function unpack(rows, key) {
//                 return rows.map(function(row)
//                 { return row[key]; });
//             }

//             var trace = {
//             x: unpack(rows, 'x'),
//             y: unpack(rows, 'z'),
//             mode: mode,
//             name: player,
//             marker: {
//                 color: random_color,
//                 size: 3,
//                 symbol: 'circle',
//             },
//             type: type
//             };
//             data.push(trace);
//         });
//     }

//     for(var i = 0; i < 40; i++){
//         createTrace(data, folder_path + event_name + '/' + i.toString() + '_' + event_name + '.csv', 'Player ' + i.toString(), 'markers', 'scatter3d', random_color[i])
//     }
    
//     var layout = {
//         title: event_name,
//         autosize: true,
//         width: 900,
//         height: 900,
//         margin: {
//             l: 0,
//             r: 0,
//             b: 0,
//             t: 65
//         }
//     };
    
//     setTimeout(function(){ console.log(data); Plotly.newPlot(div_name, data, layout, {responsive: true, displaylogo: false}); }, 5000);
// }

// set the same random color per player, instead of per plot
// var random_color = [];
// for (i = 0; i < 40; i++) {
//     r = Math.floor(Math.random() * 256).toString()
//     g = Math.floor(Math.random() * 256).toString()
//     b = Math.floor(Math.random() * 256).toString()
//     random_color[i] = 'rgb(' + r + ',' + g + ',' + b +')';
// }

// createPlot('./data/overworld/', 'PlayerMoveEvent', 'Overworld_Move_2DPoints', random_color);

// function onChange(selection){
//     var worldSelector = document.getElementById("WorldSelect");
//     var eventSelector = document.getElementById("EventSelect");

//     var plotID = worldSelector.value + eventSelector.value;
//     var titleID = worldSelector.value + "_title";
//     console.log(plotID)

//     var maps = document.getElementsByClassName("map");
//     var titles = document.getElementsByClassName("worldTitle");

//     for(var i = 0; i < titles.length; i++){
//         titles[i].style.display = "none";
//     }

//     for(var i = 0; i < maps.length; i++){
//         maps[i].style.display = "none";
//     }

//     document.getElementById(titleID).style.display = "block";
//     document.getElementById(plotID).style.display = "block";
// }

// createPlot('./data/overworld/', 'PlayerDeathEvent', 'Overworld_Death_2DPoints', random_color);
// createPlot('./data/overworld/', 'BlockBreakEvent', 'Overworld_BlockBreak_2DPoints', random_color);
// createPlot('./data/overworld/', 'BlockPlaceEvent', 'Overworld_BlockPlace_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerJoinEvent', 'Overworld_Join_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerQuitEvent', 'Overworld_Quit_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerChangedWorldEvent', 'Overworld_ChangeWorld_2DPoints', random_color);
// createPlot('./data/overworld/', 'EntityDamageByEntityEvent', 'Overworld_Damage_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerInteractEvent', 'Overworld_Interact_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerRespawnEvent', 'Overworld_Respawn_2DPoints', random_color);
// createPlot('./data/overworld/', 'ProjectileHitEvent', 'Overworld_ProjectileHit_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerDropItemEvent', 'Overworld_DropItem_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBedEnterEvent', 'Overworld_BedEnter_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBedLeaveEvent', 'Overworld_BedLeave_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerLeashEvent', 'Overworld_Leash_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerUnleashEvent', 'Overworld_Unleash_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerShearEvent', 'Overworld_Shear_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBucketFillEvent', 'Overworld_BucketFill_2DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBucketEmptyEvent', 'Overworld_BucketEmpty_2DPoints', random_color);
// createPlot('./data/overworld/', 'EnchantItemEvent', 'Overworld_Enchant_2DPoints', random_color);

// createPlot('./data/nether/', 'PlayerMoveEvent', 'Nether_Move_2DPoints', random_color);
// createPlot('./data/nether/','PlayerDeathEvent', 'Nether_Death_2DPoints', random_color);
// createPlot('./data/nether/', 'BlockBreakEvent', 'Nether_BlockBreak_2DPoints', random_color);
// createPlot('./data/nether/', 'BlockPlaceEvent', 'Nether_BlockPlace_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerJoinEvent', 'Nether_Join_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerQuitEvent', 'Nether_Quit_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerChangedWorldEvent', 'Nether_ChangeWorld_2DPoints', random_color);
// createPlot('./data/nether/', 'EntityDamageByEntityEvent', 'Nether_Damage_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerInteractEvent', 'Nether_Interact_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerRespawnEvent', 'Nether_Respawn_2DPoints', random_color);
// createPlot('./data/nether/', 'ProjectileHitEvent', 'Nether_ProjectileHit_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerDropItemEvent', 'Nether_DropItem_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBedEnterEvent', 'Nether_BedEnter_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBedLeaveEvent', 'Nether_BedLeave_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerLeashEvent', 'Nether_Leash_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerUnleashEvent', 'Nether_Unleash_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerShearEvent', 'Nether_Shear_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBucketFillEvent', 'Nether_BucketFill_2DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBucketEmptyEvent', 'Nether_BucketEmpty_2DPoints', random_color);
// createPlot('./data/nether/', 'EnchantItemEvent', 'Nether_Enchant_2DPoints', random_color);

// createPlot('./data/end/','PlayerMoveEvent', 'End_Move_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerDeathEvent', 'End_Death_2DPoints', random_color);
// createPlot('./data/end/', 'BlockBreakEvent', 'End_BlockBreak_2DPoints', random_color);
// createPlot('./data/end/', 'BlockPlaceEvent', 'End_BlockPlace_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerJoinEvent', 'End_Join_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerQuitEvent', 'End_Quit_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerChangedWorldEvent', 'End_ChangeWorld_2DPoints', random_color);
// createPlot('./data/end/', 'EntityDamageByEntityEvent', 'End_Damage_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerInteractEvent', 'End_Interact_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerRespawnEvent', 'End_Respawn_2DPoints', random_color);
// createPlot('./data/end/', 'ProjectileHitEvent', 'End_ProjectileHit_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerDropItemEvent', 'End_DropItem_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerBedEnterEvent', 'End_BedEnter_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerBedLeaveEvent', 'End_BedLeave_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerLeashEvent', 'End_Leash_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerUnleashEvent', 'End_Unleash_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerShearEvent', 'End_Shear_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerBucketFillEvent', 'End_BucketFill_2DPoints', random_color);
// createPlot('./data/end/', 'PlayerBucketEmptyEvent', 'End_BucketEmpty_2DPoints', random_color);
// createPlot('./data/end/', 'EnchantItemEvent', 'End_Enchant_2DPoints', random_color);


// // from http://bl.ocks.org/mbostock/4349187
// // Sample from a normal distribution with mean 0, stddev 1.

// function normal() {
//     var x = 0,
//         y = 0,
//         rds, c;
//     do {
//         x = Math.random() * 2 - 1;
//         y = Math.random() * 2 - 1;
//         rds = x * x + y * y;
//     } while (rds == 0 || rds > 1);
//     c = Math.sqrt(-2 * Math.log(rds) / rds); // Box-Muller transform
//     return x * c; // throw away extra sample y * c
// }

// var N = 2000,
//   a = -1,
//   b = 1.2;

// var step = (b - a) / (N - 1);
// var t = new Array(N), x = new Array(N), y = new Array(N);

// for(var i = 0; i < N; i++){
//   t[i] = a + step * i;
//   x[i] = (Math.pow(t[i], 3)) + (0.3 * normal() );
//   y[i] = (Math.pow(t[i], 6)) + (0.3 * normal() );
// }

// var trace1 = {
//   x: x,
//   y: y,
//   mode: 'markers',
//   name: 'points',
//   marker: {
//     color: 'rgb(102,0,0)',
//     size: 2,
//     opacity: 0.4
//   },
//   type: 'scatter'
// };
// var trace2 = {
//   x: x,
//   y: y,
//   name: 'density',
//   ncontours: 20,
//   colorscale: 'Hot',
//   reversescale: true,
//   showscale: false,
//   type: 'histogram2dcontour'
// };
// var trace3 = {
//   x: x,
//   name: 'x density',
//   marker: {color: 'rgb(102,0,0)'},
//   yaxis: 'y2',
//   type: 'histogram'
// };
// var trace4 = {
//   y: y,
//   name: 'y density',
//   marker: {color: 'rgb(102,0,0)'},
//   xaxis: 'x2',
//   type: 'histogram'
// };

// // var data = [trace1, trace2, trace3, trace4];
// var layout = {
//   showlegend: true,
//   autosize: false,
//   width: 1500,
//   height: 1500,
//   margin: {t: 50},
//   hovermode: 'closest',
//   bargap: 0,
//   xaxis: {
//     domain: [0, 0.85],
//     showgrid: false,
//     zeroline: false
//   },
//   yaxis: {
//     domain: [0, 0.85],
//     showgrid: false,
//     zeroline: false
//   },
//   xaxis2: {
//     domain: [0.85, 1],
//     showgrid: false,
//     zeroline: false
//   },
//   yaxis2: {
//     domain: [0.85, 1],
//     showgrid: false,
//     zeroline: false
//   }
// };
// // console.log(x);
// // console.log(y);
// // Plotly.newPlot('2D_Contour', data, layout);


// function createHeatPlot(folder_path, file_event, event_name, div_name) {
//     var data = [];


//     function createTrace(data, filename, player, mode){

//         d3.csv(filename, function(err, rows) {
        
//             function unpack(rows, key) {
//                 return rows.map(function(row)
//                 { return row[key]; });
//             }
    
//             r = Math.floor(Math.random() * 256).toString()
//             g = Math.floor(Math.random() * 256).toString()
//             b = Math.floor(Math.random() * 256).toString()
//             random_color = 'rgb(' + r + ',' + g + ',' + b +')';

//             var trace1 = {
//                 x: unpack(rows, 'x'),
//                 y: unpack(rows, 'z'),
//                 mode: mode,
//                 name: player,
//                 marker: {
//                   color: random_color,
//                   size: 3,
//                 },
//                 type: 'scatter'
//               };
//               var trace2 = {
//                 x: unpack(rows, 'x'),
//                 y: unpack(rows, 'z'),
//                 name: 'density',
//                 // ncontours: 100,
//                 colorscale: 'Hot',
//                 reversescale: true,
//                 showscale: false,
//                 type: 'histogram2dcontour'
//               };
//               var trace3 = {
//                 x: unpack(rows, 'x'),
//                 name: player + ' x density',
//                 marker: {color: random_color},
//                 yaxis: 'y2',
//                 type: 'histogram'
//               };
//               var trace4 = {
//                 y: unpack(rows, 'z'),
//                 name: 'y density',
//                 marker: {color: random_color},
//                 xaxis: 'x2',
//                 type: 'histogram'
//               };
//             data.push(trace1, trace2, trace3, trace4);
//         });
//     }


//     for(var i = 0; i < 40; i++){
//         createTrace(data, folder_path + i.toString() + file_event + '.csv', 'Player ' + i.toString(), 'markers');

//         // console.log(x_arr)
//     }
    
//     var layout = {
//         title: event_name,
//         showlegend: true,
//         autosize: false,
//         width: 1500,
//         height: 1500,
//         margin: {t: 50},
//         hovermode: 'closest',
//         bargap: 0,
//         xaxis: {
//           showgrid: false,
//           zeroline: false
//         },
//         yaxis: {
//           showgrid: false,
//           zeroline: false
//         },
//         xaxis2: {
//           domain: [0.95, 1],
//           showgrid: false,
//           zeroline: false
//         },
//         yaxis2: {
//           domain: [0.95, 1],
//           showgrid: false,
//           zeroline: false
//         }
//       };
    
//     setTimeout(function(){ console.log(data); Plotly.newPlot(div_name, data, layout, {responsive: true, displaylogo: false}); }, 5000);
// }

// createHeatPlot('./data/end/', '_move', 'PlayerMoveEvent', 'End_Move_2DHeat');

$(function(){
    $("#Event_2DPoints").load("./plots/2d/overworld/PlayerDeathEvent.html");
});

function onChange(selection){

    var worldSelector = document.getElementById("WorldSelect");
    var eventSelector = document.getElementById("EventSelect");

    var plotID = worldSelector.value + eventSelector.value;
    var titleID = worldSelector.value + "_title";
    console.log(plotID)

    var maps = document.getElementsByClassName("map");
    var titles = document.getElementsByClassName("worldTitle");

    for(var i = 0; i < titles.length; i++){
      titles[i].style.display = "none";
    }

    document.getElementById(titleID).style.display = "block";


    $("#Event_2DPoints").load("plots/2d/"+ worldSelector.value + "/" + eventSelector.value + ".html");
}