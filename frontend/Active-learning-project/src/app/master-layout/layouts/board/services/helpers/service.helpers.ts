
import { startedState, pausedState, completedState, archivedState, backlogState } from "../../shared/constants/card-states";
import { pauseEncouragement } from "../../shared/constants/pauseEncouragement";
import { encouragingWords } from "../../shared/constants/encouragement";
import { CardModel, CardTaskModel } from "../../models/board-model";

export const getCompletedTasks = (taskList: Array<CardTaskModel>): Array<CardTaskModel> => {
    return taskList?.filter((task: CardTaskModel) => task.complete === true)
}

export const transferCard = (fromList: Array<CardModel>, toList: Array<CardModel>, card: CardModel): void => {
    // Get backlog index
    const index = fromList.findIndex((cardObj: CardModel) => cardObj.id === card.id);

    // Add Card in the started list
    toList.push(fromList[index])

    // Remove the Card from the previous backlog list
    fromList.splice(index, 1);
}

export const getRandomCustomSuccessMessage = (): string => {
    return encouragingWords[Math.floor(Math.random() * encouragingWords.length)];
}

export const getRandomCustomPauseMessage = (): string => {
    return pauseEncouragement[Math.floor(Math.random() * pauseEncouragement.length)];
}

export const getPresentTenseFromCardState = (state: any): string => {
    switch (state) {
        case startedState:
            return "start";
        case pausedState:
            return "pause";
        case completedState:
            return "complete";
        case archivedState:
            return "archive";
    }
    return backlogState;
}

export const getPastTenseFromCardState = (state: any): string => {
    if (state === backlogState) return "backlogged";
    return state;
}