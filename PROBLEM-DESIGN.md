# Problem Specifications

### Define the Problem

The problem is to design a Howitzer firing simulator in order to train soldiers on the kinematics and dynamics of it firing a projectile across an empty field.

### Do Background Research:

A Howitzer is a artillery weapon commonly used in battery formations, it is characterized by its shorter barrel and arched projectile trajectory. It is like a cross between cannon and a gun. Weighing around 15,000lbs, the shells can reach a speed of up to 1600 m/s. It can fire at both high and low angles having a range of 0&deg;-90&deg; making it very versatile. The average shell is 95 lbs and 60 cm in length. The physics of the projectile can be described by rigid body kinematics and these properties will form the basis of our simulator. We are going to model ours after the M777 Howitzer.

### Rigid Body Kinematics:

Kinematics is a sub field of physics that helps us describe the motion of objects in the case a rigid body.

#### Formulas:

<b>Formula for any object expressed in an interior frame:</b>

p(t) = v(t)

mv(t) = f(t) + f<sub>d</sub>(t) + mg

<b>Formula for the drag force: </b>

f<sub>d</sub>(t) = -1/2CpAu(t)<sup>2</sup>

# Design Requirements

### Properties / Objectives:
- Write a program that simulates a Howitzer in the programming language of Java.
- The Howitzer simulation should be model behaviour as best as possible; with correct speed, weight, angles of rotation, barrel height and so on. 
- The program's physic logic should simulate in a 3D plane, with 3 dimensions of measure. 
- Have the ability to input different values for the variables to get different results.

### Constraints:
1. Economic Factors:
    - Cost of Project: This <u><b>project does not have a budget</b></u> therefore we are using all open source software. There is a cost in the sense of labour required by project contributors as well as any other supplies used by contributors to complete the project; but this will not be realized. 
    - Cost of Use: The equipment that will be used for development will include personal computers along with lab computers; both of these have already been purchased and do not warrant a cost of use; our repo location (GitLab) is also provided free of charge; this unfortunately results in a <u><b>lack of DevOps features.</b></u>
    <br><br/>
2. Social Factors: 
    - Time is a constraint when it comes to this project as contributors have around 2 months to complete it, therefore the <u><b>scope should be kept small</b></u> enough so that project can be completed.
    <br><br/>

3. Reliability
    - Project has to be very reliable initially, but factors such a features falling out of support will decrease the reliability long term, this can be mitigated.  Therefore project should be <u><b>reliable for the short term</b></u>.
    <br><br/>

4. Sustainability and Environmental Factors 
    - The project must be completed using technology which currently is not very sustainable so project should strive to <u><b>use sustainable alternative where possible</b></u>.
    <br><br/>

 5. Societal Impacts
    - The project should have <u><b>minimal societal impact </b></u>other then to help train soldiers or anyone else who would like to know how Howitzers work. The simulator has very limited other real world applications.
      <br><br/>  

### Simulator Variables
- barrel pose 
- radius and mass of the projectile
- drag coefficient 
- force applied to the projectile 
- initial speed
- gravity




