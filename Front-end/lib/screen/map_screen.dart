import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  LatLng _initialPositon = LatLng(10.77994190213791, 106.69902305102745);
  // List<Marker> allMarkers = [];
  Set<Marker> _markers = HashSet<Marker>();
  late GoogleMapController _mapController;

  void _onMapCreated(GoogleMapController controller) {
    _mapController = controller;

    setState(() {
      _markers.add(
        Marker(
            markerId: MarkerId("0"),
            position: LatLng(10.77994190213791, 106.69902305102745),
            infoWindow: InfoWindow(
                title: "Nhà thờ Đức Bà Sài Gòn", snippet: "Trung Tâm Sài Gòn")),
      );
    });
  }

  // @override
  // void initState() {
  //   // TODO: implement initState
  //   super.initState();
  //   allMarkers.add(Marker(
  //     markerId: MarkerId('myMarker'),
  //     draggable: false,
  //     onTap: (){
  //       print('Marker Tapped');
  //     },
  //     position: LatLng(10.777727278171197, 106.69540958467722)
  //   ));
  // }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('Google Map'),
          backgroundColor: Colors.blue[700],
        ),
        body: Stack(children: <Widget>[
          _GoogleMap(context),
        ]),
      ),
    );
  }

  Widget _GoogleMap(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: GoogleMap(
        mapType: MapType.normal,
        initialCameraPosition:
            CameraPosition(target: _initialPositon, zoom: 12),
        // markers: Set.from(allMarkers),
        markers: _markers,
        onMapCreated: _onMapCreated,
      ),
    );
  }
}
