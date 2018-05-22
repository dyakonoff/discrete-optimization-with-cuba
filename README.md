# Input data validation in [CUBA platform](https://www.cuba-platform.com/)

## Introduction

Every organization faces planning problems: providing products or services with a limited set of constrained resources (employees, assets, time and money). This class of problems s known as Constraint Satisfaction Programming (which is part of the Operations Research discipline).

This article discusses an approach of writing such applications with [CUBA platform](https://www.cuba-platform.com/) and [OptalPlanner library](http://www.optaplanner.org/). This approach allows regular developers who are not experts in [Operations Research area](https://en.wikipedia.org/wiki/Operations_research) to create such class of applications much faster than if they were creating these apps using regular J2EE and frontend approaches.

As a supplemental material, this article and example demonstrate couple methods of data validation that [CUBA platform](https://www.cuba-platform.com/) offers.

## Content
1. [Model problem description](#model-problem-description)


## Model problem description

First, let's settle down the model problem that our code example will be solving.

**Note:** If you want to check out code examples and technical details you can skip this section.

As you probably know, most of world trade are made with [containers ships](https://en.wikipedia.org/wiki/Container_ship) like this:<br />
_(all ships and containers images for the article were taken from Wikipedia)_

![APL Post-Panamax container ships Image ID: line0534, America's Coastlines. Collection Location: San Francisco.](resources/ship_and_holds.jpg)

These giant vessels can carry thousands of ISO-containers that can be [different types](https://en.wikipedia.org/wiki/Intermodal_container) but most of the vessels can carry only 20 ft, 40 ft and 45 ft containers of standard end extra height (extra height are called High Cube).

Container ships are divided into set of holds, each of them can hold 20 ft and 40 ft containers below deck level and 20, 40 and 45 ft containers above deck level.

For our task we'll implement a small cargo management system that will keep records of containers that the vessel carries.

Database schema of our application looks like that:

// TODO: Add DB diagram here

### Validation rules

1. Loading constraints:
    1. Only 20, 40 and 45 containers are allowed on a ship.
    1. 45 ft containers are allowed only above deck level.
    1. Only 20 and 40 ft containers can be loaded below deck level.
    1. If a stack has 20 ft containers in it, it should be even number of them.
    1. A pair of 20 ft containers should occupy the same position.
    1. A position is allowed to have either two 20 ft containers or one 40 or 45 ft (or none of them).
    1. There should be no more than one high-cube container in any container stack.
    1. Each hold has maximum allowed number of levels above deck that should not be exceeded.
    1. Containers are stacked on each other without gaps.
    1. For one cargo hold height of adjacent container's stacks should not be bigger than 2.
1. Container constraints:
    1. No container is allowed to have weight greater than allowed for it's type.
    1. Each container should have a serial number.
    1. This serial number follows [ISO-6346](https://en.wikipedia.org/wiki/ISO_6346) standard.
1. Cargo weight and ship balance constraints:
    1. Cargo should be balanced, in other words it's center of mass should not be further than 10% of the ship's length to the aft or bow side from it's center.
    1. Similarly, cargo's mass center should not be further than 10% of ship's width from it's centerline.
    1. Center of masses of cargo should not be above deck level.
    1. Total weight of containers loaded onto ship should not exceed it's net register tonnage (maximum cargo weight allowed for this ship).

**Optimization goal:**
At port we have a set of containers available for loading to our ship. So, We want to **maximize total amount of TEUs** (container's total volume) loaded onto the ship. Because we are getting paid per amount of TEUs moved from one port to another.

**Note:** For simplicity we are not calculating ship's balance in bottom-to-top directions.

![Ship scheme](resources/ship_scheme.jpg)


[Top](#introduction)
