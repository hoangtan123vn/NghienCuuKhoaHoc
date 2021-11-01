import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Loading extends StatelessWidget {
  final bool isShow;

  Loading({required Key key, required this.isShow}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Visibility(
      visible: isShow,
      child: Container(
        color: Colors.transparent,
        child: Center(
          child: SizedBox(
            height: 50,
            width: 50,
            child: Container(
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(10),
                color: Colors.black.withOpacity(0.5),
              ),
              child: Center(
                child:  CupertinoActivityIndicator()
              ),
            ),
          ),
        ),
      ),
    );
  }
}
