function createPlot(folder_path, event_name, div_name, random_color) {
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
            z: unpack(rows, 'y'),
            mode: mode,
            name: player,
            marker: {
                color: random_color,
                size: 3,
                symbol: 'circle',
                line: {
                    color: random_color,
                    width: 0
                }},
            line: {
                color: random_color,
                width: 1
            },
            type: type,
            };
            data.push(trace);
        });
    }

    for(var i = 0; i < 47; i++){
        createTrace(data, folder_path + event_name + '/' + i.toString() + '_' + event_name + '.csv', 'Player ' + i.toString(), 'markers', 'scatter3d', random_color[i])
    }

    var layout = {
        title: event_name,
        autosize: true,
        width: 900,
        height: 900,
        margin: {
            l: 0,
            r: 0,
            b: 0,
            t: 65
        }
    };

    setTimeout(function(){ Plotly.newPlot(div_name, data, layout, {responsive: true, displaylogo: false}); }, 5000);
}

// set the same random color per player, instead of per plot
var random_color = [];
for (i = 0; i < 48; i++) {
    r = Math.floor(Math.random() * 256).toString()
    g = Math.floor(Math.random() * 256).toString()
    b = Math.floor(Math.random() * 256).toString()
    random_color[i] = 'rgb(' + r + ',' + g + ',' + b +')';
}

// createPlot('./data/overworld/', 'PlayerMoveEvent', 'Overworld_Move_3DPoints', random_color);

// function onChange(selection){
//     var worldSelector = document.getElementById("WorldSelect");
//     var eventSelector = document.getElementById("EventSelect");

//     var plotID = worldSelector.value + eventSelector.value;
//     var titleID = worldSelector.value + "_title";
//     // console.log(plotID)

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

// createPlot('./data/overworld/', 'PlayerDeathEvent', 'Overworld_Death_3DPoints', random_color);
// createPlot('./data/overworld/', 'BlockBreakEvent', 'Overworld_BlockBreak_3DPoints', random_color);
// createPlot('./data/overworld/', 'BlockPlaceEvent', 'Overworld_BlockPlace_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerJoinEvent', 'Overworld_Join_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerQuitEvent', 'Overworld_Quit_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerChangedWorldEvent', 'Overworld_ChangeWorld_3DPoints', random_color);
// createPlot('./data/overworld/', 'EntityDamageByEntityEvent', 'Overworld_Damage_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerInteractEvent', 'Overworld_Interact_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerRespawnEvent', 'Overworld_Respawn_3DPoints', random_color);
// createPlot('./data/overworld/', 'ProjectileHitEvent', 'Overworld_ProjectileHit_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerDropItemEvent', 'Overworld_DropItem_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBedEnterEvent', 'Overworld_BedEnter_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBedLeaveEvent', 'Overworld_BedLeave_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerLeashEvent', 'Overworld_Leash_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerUnleashEvent', 'Overworld_Unleash_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerShearEvent', 'Overworld_Shear_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBucketFillEvent', 'Overworld_BucketFill_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerBucketEmptyEvent', 'Overworld_BucketEmpty_3DPoints', random_color);
// createPlot('./data/overworld/', 'EnchantItemEvent', 'Overworld_Enchant_3DPoints', random_color);
// createPlot('./data/overworld/', 'PlayerEggThrowEvent', 'Overworld_EggThrow_3DPoints', random_color);

// createPlot('./data/nether/', 'PlayerMoveEvent', 'Nether_Move_3DPoints', random_color);
// createPlot('./data/nether/','PlayerDeathEvent', 'Nether_Death_3DPoints', random_color);
// createPlot('./data/nether/', 'BlockBreakEvent', 'Nether_BlockBreak_3DPoints', random_color);
// createPlot('./data/nether/', 'BlockPlaceEvent', 'Nether_BlockPlace_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerJoinEvent', 'Nether_Join_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerQuitEvent', 'Nether_Quit_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerChangedWorldEvent', 'Nether_ChangeWorld_3DPoints', random_color);
// createPlot('./data/nether/', 'EntityDamageByEntityEvent', 'Nether_Damage_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerInteractEvent', 'Nether_Interact_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerRespawnEvent', 'Nether_Respawn_3DPoints', random_color);
// createPlot('./data/nether/', 'ProjectileHitEvent', 'Nether_ProjectileHit_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerDropItemEvent', 'Nether_DropItem_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBedEnterEvent', 'Nether_BedEnter_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBedLeaveEvent', 'Nether_BedLeave_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerLeashEvent', 'Nether_Leash_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerUnleashEvent', 'Nether_Unleash_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerShearEvent', 'Nether_Shear_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBucketFillEvent', 'Nether_BucketFill_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerBucketEmptyEvent', 'Nether_BucketEmpty_3DPoints', random_color);
// createPlot('./data/nether/', 'EnchantItemEvent', 'Nether_Enchant_3DPoints', random_color);
// createPlot('./data/nether/', 'PlayerEggThrowEvent', 'Nether_EggThrow_3DPoints', random_color);

// createPlot('./data/end/','PlayerMoveEvent', 'End_Move_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerDeathEvent', 'End_Death_3DPoints', random_color);
// createPlot('./data/end/', 'BlockBreakEvent', 'End_BlockBreak_3DPoints', random_color);
// createPlot('./data/end/', 'BlockPlaceEvent', 'End_BlockPlace_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerJoinEvent', 'End_Join_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerQuitEvent', 'End_Quit_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerChangedWorldEvent', 'End_ChangeWorld_3DPoints', random_color);
// createPlot('./data/end/', 'EntityDamageByEntityEvent', 'End_Damage_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerInteractEvent', 'End_Interact_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerRespawnEvent', 'End_Respawn_3DPoints', random_color);
// createPlot('./data/end/', 'ProjectileHitEvent', 'End_ProjectileHit_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerDropItemEvent', 'End_DropItem_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerBedEnterEvent', 'End_BedEnter_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerBedLeaveEvent', 'End_BedLeave_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerLeashEvent', 'End_Leash_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerUnleashEvent', 'End_Unleash_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerShearEvent', 'End_Shear_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerBucketFillEvent', 'End_BucketFill_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerBucketEmptyEvent', 'End_BucketEmpty_3DPoints', random_color);
// createPlot('./data/end/', 'EnchantItemEvent', 'End_Enchant_3DPoints', random_color);
// createPlot('./data/end/', 'PlayerEggThrowEvent', 'End_EggThrow_3DPoints', random_color);


// function createHeatPlot(folder_path, file_event, event_name, div_name) {
//     var data = [];

//     function createHeatTrace(data, filename, player, mode, type){

//         d3.csv(filename, function(err, rows) {
        
//             function unpack(rows, key) {
//                 return rows.map(function(row)
//                 { return row[key]; });
//             }

//             var trace = {
//             x: unpack(rows, 'x'),
//             y: unpack(rows, 'z'),
//             z: unpack(rows, 'y'),
//             mode: mode,
//             name: player,
//             marker: {
//                 color: unpack(rows, 'time'),
//                 size: 2,
//                 cauto:true,
//                 symbol: 'circle',
//                 colorscale: 'Hot',
//                 reversescale: false,
//                 line: {
//                     // color: random_color,
//                     width: 0
//                 }},
//             line: {
//                 // color: random_color,
//                 width: 1
//             },
//             type: type,
//             colorscale: 'Hot',
//             showscale: true,
//             reversescale: false,
//             };
//             data.push(trace);
//         });
//     }

//     for(var i = 0; i < 40; i++){
//         createHeatTrace(data, folder_path + i.toString() + file_event + '.csv', 'Player ' + i.toString(), 'markers', 'scatter3d')
//     }

//     var layout = {
//         title: event_name,
//         showscale: true,
//         reversescale: true,
//         autosize: true,
//         width: 1500,
//         height: 1500,
//         margin: {
//             l: 0,
//             r: 0,
//             b: 0,
//             t: 65
//         }
//     };

//     setTimeout(function(){ console.log(data); Plotly.newPlot(div_name, data, layout, {responsive: true, displaylogo: false}); }, 5000);
// }

// createHeatPlot('./data/overworld/move/', '_move', 'PlayerMoveEvent', 'Overworld_Move_3DHeat');

$(function(){
    $("#Event_3DPoints").load("./plots/3d/overworld/PlayerMoveEvent.html");
    // console.log()
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

    // for(var i = 0; i < maps.length; i++){
    //     maps[i].style.display = "none";
    // }

    document.getElementById(titleID).style.display = "block";
    // document.getElementById(plotID).style.display = "block";
    console.log(titleID)

    $("#Event_3DPoints").load("plots/3d/"+ worldSelector.value + "/" + eventSelector.value + ".html");
}




// $.each([
//     "ProjectileHitEvent",
//     "PlayerQuitEvent",
//     "PlayerBedLeaveEvent",
//     "PlayerBedEnterEvent",
//     "PlayerBucketEmptyEvent",
//     "PlayerDeathEvent",
//     "PlayerMoveEvent",
//     "PlayerBucketFillEvent",
//     "PlayerDropItemEvent",
//     "PlayerInteractEvent",
//     "PlayerLeashEntityEvent",
//     "PlayerChangedWorldEvent",
//     "PlayerRespawnEvent",
//     "EntityDamageByEntityEvent",
//     "PlayerEggThrowEvent",
//     "PlayerUnleashEntityEvent",
//     "BlockPlaceEvent",
//     "BlockBreakEvent",
//     "PlayerJoinEvent",
//     "PlayerShearEntityEvent",
//     "EnchantItemEvent"
// ], function(i,event){
//     $("#Nether_" + event + "_3DPoints").load("plots/3d/nether/" + event + ".html");
// });

// $.each([
//     "ProjectileHitEvent",
//     "PlayerQuitEvent",
//     "PlayerBedLeaveEvent",
//     "PlayerBedEnterEvent",
//     "PlayerBucketEmptyEvent",
//     "PlayerDeathEvent",
//     "PlayerMoveEvent",
//     "PlayerBucketFillEvent",
//     "PlayerDropItemEvent",
//     "PlayerInteractEvent",
//     "PlayerLeashEntityEvent",
//     "PlayerChangedWorldEvent",
//     "PlayerRespawnEvent",
//     "EntityDamageByEntityEvent",
//     "PlayerEggThrowEvent",
//     "PlayerUnleashEntityEvent",
//     "BlockPlaceEvent",
//     "BlockBreakEvent",
//     "PlayerJoinEvent",
//     "PlayerShearEntityEvent",
//     "EnchantItemEvent"
// ], function(i,event){
//     $("#End_" + event + "_3DPoints").load("plots/3d/end/" + event + ".html");
// });