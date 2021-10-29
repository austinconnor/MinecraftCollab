

function draw(scale, translatePos) {
    var canvas = $("#HeatMapCanvas")[0];
    var canvasWidth = canvas.width;
    var canvasHeight = canvas.height;
    var ctx = canvas.getContext("2d");
    var canvasData = ctx.getImageData(0, 0, canvasWidth, canvasHeight);
    ctx.translate(translatePos.x, translatePos.y);
    ctx.scale(scale, scale);

    // That's how you define the value of a pixel //
    function drawPixel (x, y, r, g, b, a) {
        var index = (x + y * canvasWidth) * 4;

        canvasData.data[index + 0] = r;
        canvasData.data[index + 1] = g;
        canvasData.data[index + 2] = b;
        canvasData.data[index + 3] = a;
    }

    // That's how you update the canvas, so that your //
    // modification are taken in consideration //
    function updateCanvas() {
        ctx.putImageData(canvasData, 0, 0);
    }


    for(var i = 0; i < canvasWidth; i++){
        for(var j = 0; j < canvasHeight; j++){
            r = Math.floor(Math.random() * 256);
            g = Math.floor(Math.random() * 256);
            b = Math.floor(Math.random() * 256);
            a = 255;
            drawPixel(i, j, r, g, b, a);
        }
    }
    
    updateCanvas();
}

window.onload = function() {
    var canvas = $("#HeatMapCanvas")[0];

    var translatePos = {
        x: canvas.width / 2,
        y: canvas.height / 2
    };

    var scale = 1.0;
    var scaleMultiplier = 0.8;
    var startDragOffset = {};
    var mouseDown = false;

    // add button event listeners
    $("#plus")[0].addEventListener("click", function() {
        scale /= scaleMultiplier;
        draw(scale, translatePos);
    }, false);

    $("#minus")[0].addEventListener("click", function() {
        scale *= scaleMultiplier;
        draw(scale, translatePos);
    }, false);

    // add event listeners to handle screen drag
    canvas.addEventListener("mousedown", function(evt) {
        mouseDown = true;
        startDragOffset.x = evt.clientX - translatePos.x;
        startDragOffset.y = evt.clientY - translatePos.y;
    });

    canvas.addEventListener("mouseup", function(evt) {
        mouseDown = false;
    });

    canvas.addEventListener("mouseover", function(evt) {
        mouseDown = false;
    });

    canvas.addEventListener("mouseout", function(evt) {
        mouseDown = false;
    });

    canvas.addEventListener("mousemove", function(evt) {
        if (mouseDown) {
            translatePos.x = evt.clientX - startDragOffset.x;
            translatePos.y = evt.clientY - startDragOffset.y;
            draw(scale, translatePos);
        }
    });

    draw(scale, translatePos);
};

jQuery(document).ready(function() {
    $("#wrapper").mouseover(function(e) {
        $('#status').html(e.pageX + ', ' + e.pageY);
    });
})
