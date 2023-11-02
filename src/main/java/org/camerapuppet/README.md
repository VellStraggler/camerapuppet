# <ins>Camera Puppet</ins>
#### *ver 0.0.1*

### Rundown:
*Camera Puppet* is motion-capture software that translates
the posing of a person in-shot into the posing of a "puppet" onto
a video or image,
meaning a 2-dimensional character stored in the form of images
of body parts.

This program takes a video or a photo as input, and
returns a video or photo as output. Both are to be placed
in their respective folders "input" and "output".

It also takes a collection of puppet parts as input, which are
to be placed in the folder "puppet-parts". One of each of the 
following parts may or may not be included:
* *eye.png*
* *eyebrow.png*
* *mouth.png*
* *head.png* - this includes the hair, and does not stretch
* *torso.png* - this includes the neck, and does not bend
* *arm.png* - this includes the hand, and does not bend
* *leg.png* - this includes the foot, and does not bend

For simplicity's sake, all parts are mirrored, and each of these should be
the parts on the **left** side of the puppet body.

### Puppet Requirements:
1. You must have an eyebrow for each eye
2. You must have a singular skin tone
3. The background must be static 
4. Only one person is allowed in-frame

### Out of Current Scope:
* bendable puppet parts (joints)
* a nose
* feet
* side-views of any puppet parts