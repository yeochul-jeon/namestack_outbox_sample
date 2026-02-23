#!/usr/bin/env python3
"""Mock User Service"""

from flask import Flask, jsonify, request
from datetime import datetime

app = Flask(__name__)

# Mock user data
USERS = {
    "user-001": {
        "id": "user-001",
        "name": "John Doe",
        "email": "john@example.com",
        "role": "admin",
        "created_at": "2023-01-01T00:00:00Z"
    },
    "user-002": {
        "id": "user-002",
        "name": "Jane Smith",
        "email": "jane@example.com",
        "role": "user",
        "created_at": "2023-01-02T00:00:00Z"
    },
    "user-003": {
        "id": "user-003",
        "name": "Bob Johnson",
        "email": "bob@example.com",
        "role": "user",
        "created_at": "2023-01-03T00:00:00Z"
    }
}

@app.route('/health', methods=['GET'])
def health():
    """Health check endpoint"""
    return jsonify({
        "status": "UP",
        "service": "user-service",
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }), 200

@app.route('/users/<user_id>', methods=['GET'])
def get_user(user_id):
    """Get user by ID"""
    if user_id in USERS:
        return jsonify(USERS[user_id]), 200
    return jsonify({"error": "User not found"}), 404

@app.route('/users', methods=['GET'])
def list_users():
    """List all users"""
    return jsonify(list(USERS.values())), 200

@app.route('/users', methods=['POST'])
def create_user():
    """Create new user"""
    data = request.get_json()
    user_id = f"user-{len(USERS)+1:03d}"
    new_user = {
        "id": user_id,
        "name": data.get("name", "Unknown"),
        "email": data.get("email", ""),
        "role": data.get("role", "user"),
        "created_at": datetime.utcnow().isoformat() + "Z"
    }
    USERS[user_id] = new_user
    return jsonify(new_user), 201

@app.route('/users/<user_id>', methods=['PUT'])
def update_user(user_id):
    """Update user"""
    if user_id not in USERS:
        return jsonify({"error": "User not found"}), 404

    data = request.get_json()
    USERS[user_id].update(data)
    return jsonify(USERS[user_id]), 200

@app.route('/users/<user_id>', methods=['DELETE'])
def delete_user(user_id):
    """Delete user"""
    if user_id in USERS:
        del USERS[user_id]
        return jsonify({"message": "User deleted"}), 204
    return jsonify({"error": "User not found"}), 404

if __name__ == '__main__':
    print("Starting User Service on port 8081...")
    app.run(host='0.0.0.0', port=8081, debug=False)
