import { UsersList } from './user-list.interface';

export const FakeUsers: UsersList[] = [
  {
    id: 1,
    name: 'Mushe Mudzanani',
    course: 'JAVA BASICS',
    email: 'mudzanani@gmail.com',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINEE'],
    action: 'Activate',
    avatar:
      'https://avatars.dicebear.com/v2/female/267fe04faf6405c148bd1d9376857c7e.svg',
  },
  {
    id: 2,
    name: 'Admin Super',
    email: 'admin.super@gmai.com',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['ADMIN', 'SUPER_ADMIN'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 3,
    name: 'Mushe Mudzanani',
    email: 'train@gmail.com',
    course: 'SPRING BOOT',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINEE'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 4,
    name: 'Mukwevho Arinao',
    email: 'mukwevho@gmail.coom',
    course: 'JAVA BASICS',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINEE'],
    action: 'Activate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 5,
    name: 'Ronald Nkadimeng',
    email: 'ronald@gmail.com',
    course: 'c# BASICS',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINEE'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 6,
    name: 'Rotondwa Mukhwathi',
    email: 'rotondwa@gmail.com',
    course: 'JAVA',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['ADMIN'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 7,
    name: 'Super Admin',
    email: 'super_admin@gmail.com',
    course: 'JAVA',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['SUPER_ADMIN'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 8,
    name: 'Trainer Trainee',
    email: 'trainer.trainee@gmail.com',
    course: 'C#',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINER', 'TRAINEE'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 9,
    name: 'Super Trainer Trainer',
    email: 'super.trainer.trainee@gmail.com',
    course: 'JAVA',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['SUPER_ADMIN', 'TRAINER', 'TRAINEE'],
    action: 'Deactivate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
  {
    id: 10,
    name: 'Trainee Beginer',
    email: 'trainee.beginer@gmail.com',
    course: 'JAVA',
    joined: new Date(),
    'last seen': new Date(),
    roles: ['TRAINEE'],
    action: 'Activate',
    avatar:
      'https://gravatar.com/avatar/e6eb797a806976fd7dcb8361d7bd9c1f?s=400&d=robohash&r=x',
  },
];
