package com.example.bff.dto;

import java.util.List;

public class DashboardResponse {
    private User user;
    private List<Order> orders;
    private List<Payment> payments;
    private DashboardSummary summary;

    public DashboardResponse() {
    }

    public DashboardResponse(User user, List<Order> orders, List<Payment> payments) {
        this.user = user;
        this.orders = orders;
        this.payments = payments;
        this.summary = calculateSummary(orders, payments);
    }

    public DashboardResponse(User user, List<Order> orders, List<Payment> payments, DashboardSummary summary) {
        this.user = user;
        this.orders = orders;
        this.payments = payments;
        this.summary = summary;
    }

    private static DashboardSummary calculateSummary(List<Order> orders, List<Payment> payments) {
        DashboardSummary summary = new DashboardSummary();

        if (orders != null) {
            summary.totalOrders = orders.size();
            summary.totalSpent = orders.stream()
                .mapToDouble(Order::getTotal)
                .sum();
        }

        if (payments != null) {
            summary.completedPayments = (int) payments.stream()
                .filter(p -> "completed".equals(p.getStatus()))
                .count();
        }

        return summary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public DashboardSummary getSummary() {
        return summary;
    }

    public void setSummary(DashboardSummary summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "user=" + user +
                ", orders=" + orders +
                ", payments=" + payments +
                ", summary=" + summary +
                '}';
    }

    public static class DashboardSummary {
        private int totalOrders;
        private double totalSpent;
        private int completedPayments;

        public DashboardSummary() {
        }

        public DashboardSummary(int totalOrders, double totalSpent, int completedPayments) {
            this.totalOrders = totalOrders;
            this.totalSpent = totalSpent;
            this.completedPayments = completedPayments;
        }

        public int getTotalOrders() {
            return totalOrders;
        }

        public void setTotalOrders(int totalOrders) {
            this.totalOrders = totalOrders;
        }

        public double getTotalSpent() {
            return totalSpent;
        }

        public void setTotalSpent(double totalSpent) {
            this.totalSpent = totalSpent;
        }

        public int getCompletedPayments() {
            return completedPayments;
        }

        public void setCompletedPayments(int completedPayments) {
            this.completedPayments = completedPayments;
        }

        @Override
        public String toString() {
            return "DashboardSummary{" +
                    "totalOrders=" + totalOrders +
                    ", totalSpent=" + totalSpent +
                    ", completedPayments=" + completedPayments +
                    '}';
        }
    }
}
