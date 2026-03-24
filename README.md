# OOPFinalProject

## Maze Generation and Solving Application
### Project Overview
This project is a traffic light simulation built in Java. The system models the behavior of traffic lights at an intersection and demonstrates how different components interact in a modular and extensible way. The simulation runs in real time, updating traffic light states (red, green, yellow) and notifying other entities (such as cars) of state changes.

### Team Information
    Names: David Connor Short 
    Java Version: openjdk 25.0.1
    Course: CSCI 4448 - Object-Oriented Analysis and Design
    
### Design Patterns Implemented
This project demonstrates four core design patterns:
1. Strategy Pattern

    - Purpose: To allow the traffic light timing behavior to be interchangeable without modifying the core TrafficLight logic.
    - Implementation: A TimingStrategy interface defines methods for light durations (red, green, yellow). Concrete implementations such as FixedTimingStrategy provide specific timing behavior. The TrafficLight receives a TimingStrategy via constructor injection and delegates timing decisions to it.
    - Benefit: This allows different timing behaviors to be added or changed without modifying existing code, improving flexibility and adhering to the open/closed principle.

2. State Pattern

    - Purpose: To encapsulate the behavior of each traffic light state and eliminate large conditional statements.
    - Implementation: An ILightState interface defines behavior for each state. Concrete classes implement state-specific logic and handle transitions by updating the TrafficLight’s current state. The TrafficLight delegates its behavior to the current state object.
    - Benefit: This removes the need for if-else or switch statements, promotes polymorphism, and makes it easy to add or modify states without affecting other parts of the system.

3. Observer Pattern

    - Purpose: To allow other components to react to traffic light changes without tight coupling.
    - Implementation: The TrafficLight acts as a subject and maintains a list of observers implementing an IObserver interface. When the light updates, it notifies all observers by calling their update() method with the current LightColor.
    - Benefit: This decouples the traffic light from the objects that depend on it, making the system more modular and extensible. New observers can be added without modifying existing code.

4. Factory Pattern

    - Purpose: To centralize and abstract the creation of traffic light objects.
    - Implementation: A TrafficLightFactory class provides methods to create configured TrafficLight instances with predefined states and strategies. This removes object creation logic from client code such as Main.
    - Benefit: This simplifies object creation, improves code organization, and makes it easier to manage complex initialization logic in one place.
