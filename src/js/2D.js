function createPlot(folder_path, file_event, event_name, div_name, random_color) {
    var data = [];

    function createTrace(data, filename, player, mode, type, random_color){

        d3.csv(filename, function(err, rows) {
        
            function unpack(rows, key) {
                return rows.map(function(row)
                { return row[key]; });
            }

            var trace = {
            x: unpack(rows, 'x'),
            y: unpack(rows, 'z'),
            mode: mode,
            name: player,
            marker: {
                color: random_color,
                size: 3,
                symbol: 'circle',
            },
            type: type
            };
            data.push(trace);
        });
    }

    for(var i = 0; i < 10; i++){
        createTrace(data, folder_path + i.toString() + file_event + '.csv', 'Player ' + i.toString(), 'markers', 'scatter', random_color[i])
    }
    
    var layout = {
        title: event_name,
        autosize: true,
        width: 1500,
        height: 1500,
        margin: {
            l: 0,
            r: 0,
            b: 0,
            t: 65
        }
    };
    
    setTimeout(function(){ console.log(data); Plotly.newPlot(div_name, data, layout, {responsive: true, displaylogo: false}); }, 5000);
}

// set the same random color per player, instead of per plot
var random_color = [];
for (i = 0; i < 40; i++) {
    r = Math.floor(Math.random() * 256).toString()
    g = Math.floor(Math.random() * 256).toString()
    b = Math.floor(Math.random() * 256).toString()
    random_color[i] = 'rgb(' + r + ',' + g + ',' + b +')';
}

createPlot('./data/overworld/move/', '_move', 'PlayerMoveEvent', 'Overworld_Move_2DPoints', random_color);
createPlot('./data/overworld/death/', '_death', 'PlayerDeathEvent', 'Overworld_Death_2DPoints', random_color);
createPlot('./data/overworld/block_break/', '_block_break', 'BlockBreakEvent', 'Overworld_BlockBreak_2DPoints', random_color);
createPlot('./data/overworld/block_place/', '_block_place', 'BlockPlaceEvent', 'Overworld_BlockPlace_2DPoints', random_color);

createPlot('./data/nether/move/', '_move', 'PlayerMoveEvent', 'Nether_Move_2DPoints', random_color);
createPlot('./data/nether/death/', '_death', 'PlayerDeathEvent', 'Nether_Death_2DPoints', random_color);
createPlot('./data/nether/block_break/', '_block_break', 'BlockBreakEvent', 'Nether_BlockBreak_2DPoints', random_color);
createPlot('./data/nether/block_place/', '_block_place', 'BlockPlaceEvent', 'Nether_BlockPlace_2DPoints', random_color);

createPlot('./data/end/move/', '_move', 'PlayerMoveEvent', 'End_Move_2DPoints', random_color);
createPlot('./data/end/death/', '_death', 'PlayerDeathEvent', 'End_Death_2DPoints', random_color);
createPlot('./data/end/block_break/', '_block_break', 'BlockBreakEvent', 'End_BlockBreak_2DPoints', random_color);
createPlot('./data/end/block_place/', '_block_place', 'BlockPlaceEvent', 'End_BlockPlace_2DPoints', random_color);


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