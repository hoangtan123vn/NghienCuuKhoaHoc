package com.onion.entity;

public class RelocationMove
{
   public int fromRoute; // From which route we remove the customer
   public int toRoute; // To which route we insert the customer
   public int positionOfRelocated; // In which position (of the initial route) we find the customer we want to relocate
   public int positionToBeInserted; // In which position (of the new route) we want to insert the customer
   public long fromMoveCost; // Move Cost for the route from which we remove a customer
   public long toMoveCost; // Move Cost for the route to which we add a customer
   public long moveCost; // Total move cost = fromMoveCost + toMoveCost
   public  int fromUpdLoad; // Updated load for the route from which we remove a customer
   public int toUpdLoad; // Updated load for the route to which we insert a customer

    public RelocationMove()
    {
    }
}
