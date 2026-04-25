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

    - Purpose: To allow the traffic behavior to be interchangeable without modifying core logic.
    - Implementation: A MovementStrategy interface defines methods for car behavior (Distance from light and other cars, spawn rate). Concrete implementations such as RushMovementStrategy provide specific traffic behavior. The Intersection receives a MovementStrategy via constructor injection and delegates traffic behavior decisions to it.
    - Benefit: This allows different traffic behaviors to be added or changed without modifying existing code, improving flexibility and adhering to the open/closed principle.

2. State Pattern

    - Purpose: To encapsulate the behavior of each traffic light state and eliminate large conditional statements.
    - Implementation: An ILightState interface defines behavior for each state. Concrete classes implement state-specific logic and handle transitions by updating the TrafficLight’s current state. The TrafficLight delegates its behavior to the current state object.
    - Benefit: This removes the need for if-else or switch statements, promotes polymorphism, and makes it easy to add or modify states without affecting other parts of the system.

3. Decorator Pattern

    - Purpose: To dynamically extend car behavior (such as speed, size, and appearance) without modifying the base Car class or creating a large number of subclasses.
    - Implementation: The system defines a base ICar / BasicCar, which represents a standard vehicle. Additional behaviors are added using decorators such as SportsCar and Truck, which wrap existing car objects and modify their properties (e.g., speed, color, or size).
    - Benefit: This avoids subclass explosion and allows flexible, runtime composition of car behaviors. New car types can be introduced without modifying existing classes.

4. Facade Pattern

    - Purpose: To provide a simplified interface for interacting with the complex traffic simulation system.
    - Implementation: A TrafficSimulationFacade class acts as the single entry point between the UI and the simulation engine. Instead of the UI directly interacting with multiple subsystems, it communicates only with the facade. The facade internally delegates responsibilities such as:
        - Updating the simulation state
        - Managing pause, resume, reset functionality
        - Applying global simulation speed scaling
    - Benefit: This pattern significantly reduces coupling between the UI and the simulation logic. It centralizes control logic into a single class, making the system easier to understand, maintain, and extend. It also ensures that changes to internal simulation components do not affect the UI layer.
