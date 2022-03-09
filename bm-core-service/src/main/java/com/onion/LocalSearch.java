package com.onion;

import com.onion.entity.Customer;
import com.onion.entity.Depot;
import com.onion.entity.RelocationMove;
import com.onion.entity.Solution;

import java.util.ArrayList;

public class LocalSearch implements Solver {
    private final int numberOfVehicles;
    private final int numberOfCustomers;
    private final ArrayList<Customer> allNodes;
    private final Depot depot;
    private final double[][] distanceMatrix;
    private Solution solution;

    public LocalSearch(int numberOfCustomers, int numberOfVehicles, Depot depot, double[][] distanceMatrix, ArrayList<Customer> allNodes) {
        this.depot = depot;
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfVehicles = numberOfVehicles;
        this.distanceMatrix = distanceMatrix;
        this.allNodes = allNodes;
    }

    @Override
    public void run() {
        boolean terminationCondition = false;
        int localSearchIterator = 0;

        RelocationMove relocationMove = new RelocationMove(-1, -1, 0, Double.MAX_VALUE);

        while (!terminationCondition) {
            findBestRelocationMove(relocationMove, solution, distanceMatrix, numberOfVehicles);

            if (relocationMove.getMoveCost() < 0) {
                applyRelocationMove(relocationMove, solution, distanceMatrix);
                localSearchIterator = localSearchIterator + 1;

            } else {
                terminationCondition = true;
            }

        }
    }

    @Override
    public Solution getSolution() {
        return solution;
    }

    @Override
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    private void applyRelocationMove(RelocationMove relocationMove, Solution currentSolution, double[][] distanceMatrix) {
        Customer relocatedNode = currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().get(relocationMove.getPositionOfRelocated());

        currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().remove(relocationMove.getPositionOfRelocated());

        if (relocationMove.getPositionToBeInserted() < relocationMove.getPositionOfRelocated()) {
            currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().add(relocationMove.getPositionToBeInserted() + 1, relocatedNode);
        } else {
            currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().add(relocationMove.getPositionToBeInserted(), relocatedNode);
        }

        double newSolutionCost = 0;

        for (int i = 0; i < currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().size() - 1; i++) {
            Customer A = currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().get(i);
            Customer B = currentSolution.getRoute().get(relocationMove.getRoute()).getCustomers().get(i + 1);
            newSolutionCost = newSolutionCost + distanceMatrix[A.getId_customer()][B.getId_customer()];
        }
        if (currentSolution.getRoute().get(relocationMove.getRoute()).getCost() + relocationMove.getMoveCost() != newSolutionCost) {
            System.out.println("Something went wrong with the cost calculations !!!!");
        }

        currentSolution.setCost(currentSolution.getCost() + relocationMove.getMoveCost());
        currentSolution.getRoute().get(relocationMove.getRoute()).setCost(currentSolution.getRoute().get(relocationMove.getRoute()).getCost() + relocationMove.getMoveCost());

        setSolution(currentSolution);
    }

    private void findBestRelocationMove(RelocationMove relocationMove, Solution currentSolution, double[][] distanceMatrix, int numberOfVehicles) {
        double bestMoveCost = Double.MAX_VALUE;

        for (int j = 0; j < numberOfVehicles; j++) {
            for (int relIndex = 1; relIndex < currentSolution.getRoute().get(j).getCustomers().size() - 1; relIndex++) {
                Customer A = currentSolution.getRoute().get(j).getCustomers().get(relIndex - 1);
                Customer B = currentSolution.getRoute().get(j).getCustomers().get(relIndex);
                Customer C = currentSolution.getRoute().get(j).getCustomers().get(relIndex + 1);

                for (int afterInd = 0; afterInd < currentSolution.getRoute().get(j).getCustomers().size() - 1; afterInd++) {
                    if (afterInd != relIndex && afterInd != relIndex - 1) {
                        Customer F = currentSolution.getRoute().get(j).getCustomers().get(afterInd);
                        Customer G = currentSolution.getRoute().get(j).getCustomers().get(afterInd + 1);

                        double costRemoved1 = distanceMatrix[A.getId_customer()][B.getId_customer()] + distanceMatrix[B.getId_customer()][C.getId_customer()];
                        double costRemoved2 = distanceMatrix[F.getId_customer()][G.getId_customer()];
                        double costRemoved = costRemoved1 + costRemoved2;

                        double costAdded1 = distanceMatrix[A.getId_customer()][C.getId_customer()];
                        double costAdded2 = distanceMatrix[F.getId_customer()][B.getId_customer()] + distanceMatrix[B.getId_customer()][G.getId_customer()];
                        double costAdded = costAdded1 + costAdded2;

                        double moveCost = costAdded - costRemoved;

                        if (moveCost < bestMoveCost) {
                            bestMoveCost = moveCost;
                            relocationMove.setPositionOfRelocated(relIndex);
                            relocationMove.setPositionToBeInserted(afterInd);
                            relocationMove.setMoveCost(moveCost);
                            relocationMove.setRoute(j);
                        }
                    }
                }
            }
        }
        setSolution(currentSolution);
    }
}