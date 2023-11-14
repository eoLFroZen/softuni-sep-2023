let coordinates = [];
const routeId = document.getElementById('routeId').value
let midCoordinates;

function renderMap() {
    fetch(`http://localhost:8080/routes/coordinates/${routeId}`)
        .then(res => res.json())
        .then(res => {

            coordinates = res;

            if (coordinates.length === 0) {
                return;
            }

            let minCoordinates = coordinates.reduce((acc, curr) =>
                [Math.min(acc[0], curr[0]), Math.min(acc[1], curr[1])]);
            let maxCoordinates = coordinates.reduce((acc, curr) =>
                [Math.max(acc[0], curr[0]), Math.max(acc[1], curr[1])]);

            let midLong = (minCoordinates[0] + maxCoordinates[0]) / 2;
            let midLat = (minCoordinates[1] + maxCoordinates[1]) / 2;
            midCoordinates = [midLong, midLat];

            mapboxgl.accessToken = 'pk.eyJ1IjoiZ29zcG9kaW5vdmdhbGluOTYiLCJhIjoiY2xudXd0enliMGd6MjJpbnM5YnE0dGNnNCJ9.7kxRpuM2zse_V7LEAqGf-A';
            let map;
            try {
                map = new mapboxgl.Map({
                    container: 'map',
                    // Choose from Mapbox's core styles, or make your own style with Mapbox Studio
                    style: 'mapbox://styles/mapbox/streets-v12',
                    center: midCoordinates,
                    zoom: 11
                })

            } catch (ignored) {
                //do nothing
                return;
            }

            map.on('load', () => {
                map.addSource('route', {
                    'type': 'geojson',
                    'data': {
                        'type': 'Feature',
                        'properties': {},
                        'geometry': {
                            'type': 'LineString',
                            'coordinates': coordinates
                        }
                    }
                });
                map.addLayer({
                    'id': 'route',
                    'type': 'line',
                    'source': 'route',
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': '#888',
                        'line-width': 8
                    }
                });
            });
        })
}

renderMap();