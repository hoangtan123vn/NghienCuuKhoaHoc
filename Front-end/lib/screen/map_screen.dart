import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_map/api/location_service.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  Completer<GoogleMapController> _Controller = Completer();

  final TextEditingController _searchController = TextEditingController();

  static final CameraPosition _DBChurch = CameraPosition(
    target: LatLng(10.77994190213791, 106.69902305102745),
    zoom: 11.5,
  );

  static final CameraPosition _DDL = CameraPosition(
    target: LatLng(10.777921200460085, 106.69530808438522),
    zoom: 11.5,
  );

  static final Marker _marker = Marker(
    markerId: MarkerId('_initialPositon'),
    infoWindow: InfoWindow(title: 'Duc Ba Church'),
    icon: BitmapDescriptor.defaultMarker,
    position: LatLng(10.77994190213791, 106.69902305102745),
  );

  static final Marker _DinhDocLapmarker = Marker(
    markerId: MarkerId('_DinhDocLapmarker'),
    infoWindow: InfoWindow(title: 'Dinh Doc Lap'),
    icon: BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueBlue),
    position: LatLng(10.777921200460085, 106.69530808438522),
  );

  static final Polyline _polyline = Polyline(
    polylineId: PolylineId('_polyline'),
    points: [
      LatLng(10.777921200460085, 106.69530808438522),
      LatLng(10.77994190213791, 106.69902305102745),
    ],
    width: 5,
  );

  static final Polygon _polygon = Polygon(
    polygonId: PolygonId('_polygon'),
    points: [
      LatLng(10.77994190213791, 106.69902305102745),
      LatLng(10.777921200460085, 106.69530808438522),
      LatLng(10.778, 106.697),
      LatLng(10.779, 106.697),
    ],
    strokeWidth: 5,
    fillColor: Colors.transparent,
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Google Map'),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Row(
              children: <Widget>[
                Expanded(
                  child: TextFormField(
                    controller: _searchController,
                    textCapitalization: TextCapitalization.words,
                    decoration: InputDecoration(hintText: 'Search by City'),
                    onChanged: (value) {
                      print(value);
                    },
                  ),
                ),
                IconButton(
                  onPressed: () async {
                    var place = await LocationService()
                        .getPlace(_searchController.text);
                    _goToPlace(place);
                  },
                  icon: Icon(Icons.search),
                ),
              ],
            ),
            Container(
              height: MediaQuery.of(context).size.height * 1.0,
              child: GoogleMap(
                // polylines: {_polyline},
                // polygons: {_polygon},
                  markers: {
                    _marker,
                    // _DinhDocLapmarker,
                  },
                  myLocationButtonEnabled: false,
                  zoomControlsEnabled: false,
                  mapType: MapType.normal,
                  initialCameraPosition: _DBChurch,
                  onMapCreated: (GoogleMapController controller) {
                    _Controller.complete(controller);
                  }),
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _DinhDocLap,
      ),
    );
  }

  Future<void> _goToPlace(Map<String, dynamic> place) async {
    final double lat = place['geometry']['location']['lat'];
    final double lng = place['geometry']['location']['lng'];

    final GoogleMapController controller = await _Controller.future;
    controller.animateCamera(
      CameraUpdate.newCameraPosition(
        CameraPosition(target: LatLng(lat, lng), zoom: 12),
      ),
    );
  }

  Future<void> _DinhDocLap() async {
    final GoogleMapController controller = await _Controller.future;
    controller.animateCamera(CameraUpdate.newCameraPosition(_DDL));
  }
}
