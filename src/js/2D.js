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

    for(var i = 0; i < 40; i++){
        createTrace(data, folder_path + i.toString() + file_event + '.csv', 'Player ' + i.toString(), 'markers', 'scattergl', random_color[i])
    }
    
    var layout = {
        title: event_name,
        autosize: true,
        width: 750,
        height: 750,
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
// createPlot('./data/overworld/death/', '_death', 'PlayerDeathEvent', 'Overworld_Death_2DPoints', random_color);
// createPlot('./data/overworld/block_break/', '_block_break', 'BlockBreakEvent', 'Overworld_BlockBreak_2DPoints', random_color);
// createPlot('./data/overworld/block_place/', '_block_place', 'BlockPlaceEvent', 'Overworld_BlockPlace_2DPoints', random_color);

// createPlot('./data/nether/move/', '_move', 'PlayerMoveEvent', 'Nether_Move_2DPoints', random_color);
// createPlot('./data/nether/death/', '_death', 'PlayerDeathEvent', 'Nether_Death_2DPoints', random_color);
// createPlot('./data/nether/block_break/', '_block_break', 'BlockBreakEvent', 'Nether_BlockBreak_2DPoints', random_color);
// createPlot('./data/nether/block_place/', '_block_place', 'BlockPlaceEvent', 'Nether_BlockPlace_2DPoints', random_color);

// createPlot('./data/end/move/', '_move', 'PlayerMoveEvent', 'End_Move_2DPoints', random_color);
// createPlot('./data/end/death/', '_death', 'PlayerDeathEvent', 'End_Death_2DPoints', random_color);
// createPlot('./data/end/block_break/', '_block_break', 'BlockBreakEvent', 'End_BlockBreak_2DPoints', random_color);
// createPlot('./data/end/block_place/', '_block_place', 'BlockPlaceEvent', 'End_BlockPlace_2DPoints', random_color);

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

    for(var i = 0; i < maps.length; i++){
        maps[i].style.display = "none";
    }

    document.getElementById(titleID).style.display = "block";
    document.getElementById(plotID).style.display = "block";
}