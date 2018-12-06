import * as React from "react";

export interface IList<T> {
    title: string;
    list: T[];
    // select: (x: number) => void;
    show: (x: T) => string;
}

const SimpleList = function <T>({title, list, show}: IList<T>) { // tslint:disable-line
    if (list.length > 0) {
        return (
            <div>
                <h1>{title}</h1>
                <ul>
                    {
                        list.map((c, i) => (
                                <li key={i}>
                                    {show(c)}
                                </li>
                            )
                        )
                    }
                </ul>
            </div>);
    }
    return null;
};

export default SimpleList;