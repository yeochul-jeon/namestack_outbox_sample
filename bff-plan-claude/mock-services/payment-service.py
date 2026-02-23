#!/usr/bin/env python3
"""Mock Payment Service"""

from flask import Flask, jsonify, request
from datetime import datetime

app = Flask(__name__)

# Mock payment data
PAYMENTS = {
    "payment-001": {
        "id": "payment-001",
        "userId": "user-001",
        "orderId": "order-001",
        "amount": 59.98,
        "method": "credit_card",
        "status": "completed",
        "created_at": "2024-01-01T10:05:00Z"
    },
    "payment-002": {
        "id": "payment-002",
        "userId": "user-002",
        "orderId": "order-002",
        "amount": 49.99,
        "method": "paypal",
        "status": "pending",
        "created_at": "2024-01-02T11:05:00Z"
    },
    "payment-003": {
        "id": "payment-003",
        "userId": "user-001",
        "orderId": "order-003",
        "amount": 59.97,
        "method": "credit_card",
        "status": "failed",
        "created_at": "2024-01-03T12:05:00Z"
    }
}

@app.route('/health', methods=['GET'])
def health():
    """Health check endpoint"""
    return jsonify({
        "status": "UP",
        "service": "payment-service",
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }), 200

@app.route('/payments/<payment_id>', methods=['GET'])
def get_payment(payment_id):
    """Get payment by ID"""
    if payment_id in PAYMENTS:
        return jsonify(PAYMENTS[payment_id]), 200
    return jsonify({"error": "Payment not found"}), 404

@app.route('/payments', methods=['GET'])
def list_payments():
    """List all payments, optionally filtered by userId"""
    user_id = request.args.get('userId')
    if user_id:
        filtered_payments = [payment for payment in PAYMENTS.values() if payment['userId'] == user_id]
        return jsonify(filtered_payments), 200
    return jsonify(list(PAYMENTS.values())), 200

@app.route('/payments', methods=['POST'])
def create_payment():
    """Create new payment"""
    data = request.get_json()
    payment_id = f"payment-{len(PAYMENTS)+1:03d}"
    new_payment = {
        "id": payment_id,
        "userId": data.get("userId", ""),
        "orderId": data.get("orderId", ""),
        "amount": data.get("amount", 0),
        "method": data.get("method", "credit_card"),
        "status": "pending",
        "created_at": datetime.utcnow().isoformat() + "Z"
    }
    PAYMENTS[payment_id] = new_payment
    return jsonify(new_payment), 201

@app.route('/payments/<payment_id>', methods=['PUT'])
def update_payment(payment_id):
    """Update payment"""
    if payment_id not in PAYMENTS:
        return jsonify({"error": "Payment not found"}), 404

    data = request.get_json()
    PAYMENTS[payment_id].update(data)
    return jsonify(PAYMENTS[payment_id]), 200

@app.route('/payments/<payment_id>', methods=['DELETE'])
def delete_payment(payment_id):
    """Delete payment"""
    if payment_id in PAYMENTS:
        del PAYMENTS[payment_id]
        return jsonify({"message": "Payment deleted"}), 204
    return jsonify({"error": "Payment not found"}), 404

if __name__ == '__main__':
    print("Starting Payment Service on port 8083...")
    app.run(host='0.0.0.0', port=8083, debug=False)
