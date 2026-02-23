#!/usr/bin/env python3
"""Mock Order Service"""

from flask import Flask, jsonify, request
from datetime import datetime

app = Flask(__name__)

# Mock order data
ORDERS = {
    "order-001": {
        "id": "order-001",
        "userId": "user-001",
        "items": [{"productId": "prod-001", "quantity": 2, "price": 29.99}],
        "total": 59.98,
        "status": "completed",
        "created_at": "2024-01-01T10:00:00Z"
    },
    "order-002": {
        "id": "order-002",
        "userId": "user-002",
        "items": [{"productId": "prod-002", "quantity": 1, "price": 49.99}],
        "total": 49.99,
        "status": "processing",
        "created_at": "2024-01-02T11:00:00Z"
    },
    "order-003": {
        "id": "order-003",
        "userId": "user-001",
        "items": [{"productId": "prod-003", "quantity": 3, "price": 19.99}],
        "total": 59.97,
        "status": "pending",
        "created_at": "2024-01-03T12:00:00Z"
    }
}

@app.route('/health', methods=['GET'])
def health():
    """Health check endpoint"""
    return jsonify({
        "status": "UP",
        "service": "order-service",
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }), 200

@app.route('/orders/<order_id>', methods=['GET'])
def get_order(order_id):
    """Get order by ID"""
    if order_id in ORDERS:
        return jsonify(ORDERS[order_id]), 200
    return jsonify({"error": "Order not found"}), 404

@app.route('/orders', methods=['GET'])
def list_orders():
    """List all orders, optionally filtered by userId"""
    user_id = request.args.get('userId')
    if user_id:
        filtered_orders = [order for order in ORDERS.values() if order['userId'] == user_id]
        return jsonify(filtered_orders), 200
    return jsonify(list(ORDERS.values())), 200

@app.route('/orders', methods=['POST'])
def create_order():
    """Create new order"""
    data = request.get_json()
    order_id = f"order-{len(ORDERS)+1:03d}"
    new_order = {
        "id": order_id,
        "userId": data.get("userId", ""),
        "items": data.get("items", []),
        "total": data.get("total", 0),
        "status": "pending",
        "created_at": datetime.utcnow().isoformat() + "Z"
    }
    ORDERS[order_id] = new_order
    return jsonify(new_order), 201

@app.route('/orders/<order_id>', methods=['PUT'])
def update_order(order_id):
    """Update order"""
    if order_id not in ORDERS:
        return jsonify({"error": "Order not found"}), 404

    data = request.get_json()
    ORDERS[order_id].update(data)
    return jsonify(ORDERS[order_id]), 200

@app.route('/orders/<order_id>', methods=['DELETE'])
def delete_order(order_id):
    """Delete order"""
    if order_id in ORDERS:
        del ORDERS[order_id]
        return jsonify({"message": "Order deleted"}), 204
    return jsonify({"error": "Order not found"}), 404

if __name__ == '__main__':
    print("Starting Order Service on port 8082...")
    app.run(host='0.0.0.0', port=8082, debug=False)
