var data = [];

function createTrace(filename, player, mode, type){
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
            color: random_color,
            width: 2
        },
        type: type
        };

        data.push(trace)
        console.log(trace)
    });
}

for(var i = 0; i < 20; i++){
    createTrace('./data/'+ i.toString() + '_move.csv', i.toString(), 'markers', 'scatter')
}

var layout = {
title: '2D Movement Plot',
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

setTimeout(function(){ console.log(data); Plotly.newPlot('my2DMap', data, layout, {responsive: true, displaylogo: false}); }, 5000);
