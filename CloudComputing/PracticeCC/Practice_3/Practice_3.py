class EventBus(object):
    def __init__(self):
        self.subscribers = {}

    def subscribe(self, event_name, callback):
        if event_name not in self.subscribers.keys():
            self.subscribers[event_name] = [callback]
        else:
            self.subscribers[event_name].append(callback)

    def publish(self, event_name, data):
        if event_name in self.subscribers.keys():
            for callback in self.subscribers[event_name]:
                callback(data)

    def unsubscribe(self, event_name, callback):
        if event_name in self.subscribers.keys():
            self.subscribers[event_name].remove(callback)
            if len(self.subscribers[event_name]) == 0:
                del self.subscribers[event_name]

bus = EventBus()

first = lambda x: print("first: "+x)
second = lambda x: print("second: "+x)

bus.subscribe("firstEvent", first)
bus.subscribe("firstEvent", second)
bus.subscribe("secondEvent", first)

bus.publish("firstEvent", "1_event")
bus.publish("secondEvent", "2_event")

bus.unsubscribe("firstEvent", first)
