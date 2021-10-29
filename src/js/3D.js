var data = [];

function createTrace(filename, player, mode, type){

    var ret;
    d3.csv(filename, function(err, rows) {
    
        function unpack(rows, key) {
            return rows.map(function(row)
            { return row[key]; });
        }

        r = Math.floor(Math.random() * 256).toString()
        g = Math.floor(Math.random() * 256).toString()
        b = Math.floor(Math.random() * 256).toString()
        random_color = 'rgb(' + r + ',' + g + ',' + b +')';
        var trace = {
        x: unpack(rows, 'x'),
        y: unpack(rows, 'z'),
        z: unpack(rows, 'y'),
        mode: mode,
        name: player,
        marker: {
            color: random_color,
            size: 2,
            symbol: 'circle',
            line: {
                color: random_color,
                width: 0
            }},
        line: {
            color: '#1f77b4',
            width: 2
        },
        type: type
        };
        data.push(trace);
        console.log(data.length)
        
    });

    
}
for(var i = 35; i < 40; i++){
    createTrace('./data/'+ i.toString() + '_move.csv', i.toString(), 'lines+markers', 'scatter3d')
}

console.log(data[1])

var layout = {
    title: 'ALL PLAYER PLOT',
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

setTimeout(function(){ console.log(data); Plotly.newPlot('my3DPoints', data, layout, {responsive: true, displaylogo: false}); }, 5000);




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

