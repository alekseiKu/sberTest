from flask import Flask, request, jsonify
from kafka import KafkaProducer

app = Flask(__name__)
producer = KafkaProducer(bootstrap_servers='localhost:9092')


@app.route('/send', methods=['POST'])
def send_message():
    message = request.json.get('message')
    producer.send('test_topic', message.encode('utf-8'))
    return jsonify({'status': 'Message sent to Kafka'}), 200


if __name__ == '__main__':
    app.run(port=5000)
