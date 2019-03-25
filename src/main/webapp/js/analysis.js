function showAnalysisScreen() {

    var token = document.getElementById('token').value;
    hideAllScreens();

    var analysis_screen = document.getElementById('analysis_screen');
    analysis_screen.style.display = 'inline-block';

    /*
    var randomScalingFactor = function () {
        return Math.round(Math.random() * 100);
    };
    */

    var config = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    //randomScalingFactor(),
                    //randomScalingFactor(),
                    //randomScalingFactor(),
                    //randomScalingFactor(),
                    //randomScalingFactor(),
                    10,
                    20,
                    30,
                    40,
                    50,
                ],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 206, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(153, 102, 255)',
                ],
                label: 'Dataset 1'
            }],
            labels: [
                'Red',
                'Orange',
                'Yellow',
                'Green',
                'Blue'
            ]
        },
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Chart.js Doughnut Chart'
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };

    var ctx = document.getElementById('chart-area').getContext('2d');
    var myChart = new Chart(ctx, config);

}