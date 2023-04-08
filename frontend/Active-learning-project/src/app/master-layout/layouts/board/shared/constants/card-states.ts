import { CardStatus } from "../../models/board-model";


const backlogState: CardStatus = 'backlog';
const startedState: CardStatus = 'started';
const pausedState: CardStatus = 'paused';
const completedState: CardStatus = 'completed';
const archivedState: CardStatus = 'archived';

export { backlogState, startedState, pausedState, completedState, archivedState }
