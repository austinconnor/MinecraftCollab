d3.csv("https://raw.githubusercontent.com/plotly/datasets/master/alpha_shape.csv", function(err, rows) {

    function unpack(rows, key) {
        return rows.map(function(row) { return row[key]; });
    }

    var data = [{
        x: unpack(rows, 'x'),
        y: unpack(rows, 'y'),
        z: unpack(rows, 'z'),
        mode: 'markers',
        type: 'scatter3d',
        marker: {
          color: 'rgb(23, 190, 207)',
          size: 2
        }
    },{
        alphahull: 7,
        opacity: 0.1,
        type: 'mesh3d',
        x: unpack(rows, 'x'),
        y: unpack(rows, 'y'),
        z: unpack(rows, 'z')
    }];

    var layout = {
        autosize: true,
        height: 1000,
        scene: {
            aspectratio: {
                x: 1,
                y: 1,
                z: 1
            },
            camera: {
                center: {
                    x: 0,
                    y: 0,
                    z: 0
                },
                eye: {
                    x: 1.25,
                    y: 1.25,
                    z: 1.25
                },
                up: {
                    x: 0,
                    y: 0,
                    z: 1
                }
            },
            xaxis: {
                type: 'linear',
                zeroline: false
            },
            yaxis: {
                type: 'linear',
                zeroline: false
            },
            zaxis: {
                type: 'linear',
                zeroline: false
            }
        },
        title: '3d point clustering',
        width: 1000
    };

    Plotly.newPlot('my3DCluster', data, layout, {responseive: true, displaylogo: false});

});

d3.csv('https://raw.githubusercontent.com/plotly/datasets/master/_3d-line-plot.csv', function(err, rows) {
    
    function unpack(rows, key) {
        return rows.map(function(row)
        { return row[key]; });
    }

    var trace1 = {
    x: unpack(rows, 'x1'),
    y: unpack(rows, 'y1'),
    z: unpack(rows, 'z1'),
    mode: 'lines+markers',
    name: "Player 1",
    marker: {
        color: 'rgb(255,20,147)',
        size: 2,
        symbol: 'circle',
        line: {
            color: 'rgb(255,20,147)',
            width: 0
        }},
    line: {
        color: '#1f77b4',
        width: 2
    },
    type: 'scatter3d'
    };

    var trace2 = {
    x: unpack(rows, 'x2'),
    y: unpack(rows, 'y2'),
    z: unpack(rows, 'z2'),
    mode: 'lines',
    name: "Player 2",
    marker: {
        color: '#9467bd',
        size: 12,
        symbol: 'circle',
        line: {
        color: 'rgb(0,0,0)',
        width: 0
        }},
    line: {
        color: 'rgb(44, 160, 44)',
        width: 1
    },
    type: 'scatter3d'
    };

    var trace3 = {
    x: unpack(rows, 'x3'),
    y: unpack(rows, 'y3'),
    z: unpack(rows, 'z3'),
    mode: 'lines',
    name: "Player 3",
    marker: {
        color: '#bcbd22',
        size: 12,
        symbol: 'circle',
        line: {
        color: 'rgb(0,0,0)',
        width: 0
        }},
    line: {
        color: '#bcbd22',
        width: 1
    },
    type: 'scatter3d'
    };

    var data = [trace1, trace2, trace3];
    var layout = {
    title: '3D Line Plot',
    autosize: true,
    width: 1000,
    height: 1000,
    margin: {
        l: 0,
        r: 0,
        b: 0,
        t: 65
    }
    };
    Plotly.newPlot('my3DLine', data, layout, {responsive: true, displaylogo: false});
});

d3.csv('https://raw.githubusercontent.com/plotly/datasets/master/_3d-line-plot.csv', function(err, rows) {
    
    function unpack(rows, key) {
        return rows.map(function(row)
        { return row[key]; });
    }

    var trace1 = {
    x: unpack(rows, 'x1'),
    y: unpack(rows, 'y1'),
    z: unpack(rows, 'z1'),
    mode: 'markers',
    name: "Player 1",
    marker: {
        color: 'rgb(255,20,147)',
        size: 2,
        symbol: 'circle',
        line: {
            color: 'rgb(255,20,147)',
            width: 0
        }},
    line: {
        color: '#1f77b4',
        width: 2
    },
    type: 'scatter3d'
    };

    var trace2 = {
    x: unpack(rows, 'x2'),
    y: unpack(rows, 'y2'),
    z: unpack(rows, 'z2'),
    mode: 'markers',
    name: "Player 2",
    marker: {
        color: '#9467bd',
        size: 2,
        symbol: 'circle',
        line: {
        color: 'rgb(0,0,0)',
        width: 0
        }},
    line: {
        color: 'rgb(44, 160, 44)',
        width: 1
    },
    type: 'scatter3d'
    };

    var trace3 = {
    x: unpack(rows, 'x3'),
    y: unpack(rows, 'y3'),
    z: unpack(rows, 'z3'),
    mode: 'markers',
    name: "Player 3",
    marker: {
        color: '#bcbd22',
        size: 2,
        symbol: 'circle',
        line: {
        color: 'rgb(0,0,0)',
        width: 0
        }},
    line: {
        color: '#bcbd22',
        width: 1
    },
    type: 'scatter3d'
    };

    var data = [trace1, trace2, trace3];
    var layout = {
    title: '3D Line Plot',
    autosize: true,
    width: 1000,
    height: 1000,
    margin: {
        l: 0,
        r: 0,
        b: 0,
        t: 65
    }
    };
    Plotly.newPlot('my3DPoints', data, layout, {responsive: true, displaylogo: false});
});


// var data = $.csv.toObjects("../../data/epilog_data.csv");

// $(document).ready(function() {
//     $.ajax({
//         type: "GET",
//         url: "http://127.0.0.1:5500/data/epilog_data.txt",
//         dataType: "text",
//         success: function(data) {processData(data);}
//      });
// });



// function processData(allText) {
//     var allTextLines = allText.split(/\r\n|\n/);
//     var headers = allTextLines[0].split(',');
//     var lines = [];

//     for (var i=1; i<allTextLines.length; i++) {
//         var data = allTextLines[i].split(',');
//         if (data.length == headers.length) {

//             var tarr = [];
//             for (var j=0; j<headers.length; j++) {
//                 tarr.push(headers[j]+":"+data[j]);
//             }
//             lines.push(tarr);
//         }
//     }
//     // alert(lines);
//     console.log(lines);
// }

// var trace1 = {
//     x: data.x1,
//     y: data.y1,
//     z: data.z1,
//     mode: 'markers',
//     name: "Player 1",
//     marker: {
//         color: 'rgb(255,20,147)',
//         size: 2,
//         symbol: 'circle',
//         line: {
//             color: 'rgb(255,20,147)',
//             width: 0
//         }},
//     line: {
//         color: '#1f77b4',
//         width: 2
//     },
//     type: 'scatter3d'
// };
        
            // var trace2 = {
            // x: unpack(rows, 'x2'),
            // y: unpack(rows, 'y2'),
            // z: unpack(rows, 'z2'),
            // mode: 'markers',
            // name: "Player 2",
            // marker: {
            //     color: '#9467bd',
            //     size: 2,
            //     symbol: 'circle',
            //     line: {
            //     color: 'rgb(0,0,0)',
            //     width: 0
            //     }},
            // line: {
            //     color: 'rgb(44, 160, 44)',
            //     width: 1
            // },
            // type: 'scatter3d'
            // };
        
            // var trace3 = {
            // x: unpack(rows, 'x3'),
            // y: unpack(rows, 'y3'),
            // z: unpack(rows, 'z3'),
            // mode: 'markers',
            // name: "Player 3",
            // marker: {
            //     color: '#bcbd22',
            //     size: 2,
            //     symbol: 'circle',
            //     line: {
            //     color: 'rgb(0,0,0)',
            //     width: 0
            //     }},
            // line: {
            //     color: '#bcbd22',
            //     width: 1
            // },
            // type: 'scatter3d'
            // };
        
// var data = [trace1];
// var layout = {
//     title: '3D Line Plot',
//     autosize: true,
//     width: 1000,
//     height: 1000,
//     margin: {
//         l: 0,
//         r: 0,
//         b: 0,
//         t: 65
//     }
// };

// Plotly.newPlot('my3DLine2', data, layout, {responsive: true, displaylogo: false});

